package org.dataMigrator;

import org.dataMigrator.downloadInfo.DownloadInfo;
import org.dataMigrator.services.DownloadService;
import org.dataMigrator.services.UploadService;
import org.dataMigrator.util.FileTransferReport;
import org.dataMigrator.util.FileTransferResult;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FileTransferService {

    private DownloadService downloadService; // Assume this service is initialized elsewhere
    private UploadService uploadService;     // Assume this service is initialized elsewhere

    public FileTransferReport transferFiles(long packageId) {
        List<DownloadInfo> downloadInfos = downloadService.getDownloadInfos(packageId);
        List<Callable<FileTransferResult>> tasks = new ArrayList<>();
        Set<String> fileNames = new HashSet<>();
        long startTime = System.currentTimeMillis();
        int totalSize = 0;

        // Enforce restrictions
        for (DownloadInfo downloadInfo : downloadInfos) {
            String originalFileName = downloadInfo.getOriginalFileName();
            if (!isFileNameValid(originalFileName) || !fileNames.add(originalFileName) || totalSize + downloadInfo.getSize() > 100_000_000) {
                continue;
            }
            totalSize += downloadInfo.getSize();
            tasks.add(() -> {
                long fileStartTime = System.currentTimeMillis();
                try (InputStream data = new java.net.URL(downloadInfo.getDownloadURL()).openStream()) {
                    uploadService.doUpload(downloadInfo.getFileKey(), data, downloadInfo.getSize());
                    return new FileTransferResult(originalFileName, System.currentTimeMillis() - fileStartTime, true);
                } catch (Exception e) {
                    return new FileTransferResult(originalFileName, System.currentTimeMillis() - fileStartTime, false);
                }
            });
        }

        // Perform download and upload concurrently
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            List<Future<FileTransferResult>> results = executor.invokeAll(tasks);
            return generateReport(results, startTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new FileTransferReport(new ArrayList<>(), System.currentTimeMillis() - startTime, 0, tasks.size());
        } finally {
            executor.shutdown();
        }
    }

    private boolean isFileNameValid(String fileName) {
        String[] forbiddenExtensions = {"cmd", "com", "dll", "dmg", "exe", "iso", "jar", "js"};
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        for (String forbidden : forbiddenExtensions) {
            if (forbidden.equals(fileExtension)) {
                return false;
            }
        }
        return true;
    }

    private FileTransferReport generateReport(List<Future<FileTransferResult>> futures, long startTime) throws InterruptedException {
        List<FileTransferResult> transferResults = new ArrayList<>();
        int successCount = 0;
        for (Future<FileTransferResult> future : futures) {
            try {
                FileTransferResult result = future.get();
                transferResults.add(result);
                if (result.isSuccess()) successCount++;
            } catch (Exception e) {

            }
        }
        int failureCount = transferResults.size() - successCount;
        long totalTime = System.currentTimeMillis() - startTime;
        return new FileTransferReport(transferResults, totalTime, successCount, failureCount);
    }
}


