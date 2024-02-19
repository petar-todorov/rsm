package org.dataMigrator.downloadInfo;

public interface DownloadInfo {
    int getSize();
    String getOriginalFileName();
    String getFileKey();
    String getDownloadURL();
}