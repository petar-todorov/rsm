package org.dataMigrator.util;

public class FileTransferResult {
    private String fileName;
    private long uploadTime; // in milliseconds
    private boolean success;
    private String message; // Success or failure reason

    public FileTransferResult(String fileName, long uploadTime, boolean success) {
        this.fileName = fileName;
        this.uploadTime = uploadTime;
        this.success = success;
    }

    // Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return String.format("File: %s, Time: %d ms, Success: %b, Message: %s",
                fileName, uploadTime, success);
    }
}
