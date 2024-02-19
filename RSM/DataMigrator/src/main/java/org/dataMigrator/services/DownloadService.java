package org.dataMigrator.services;


import org.dataMigrator.downloadInfo.DownloadInfo;

import java.util.List;

public interface DownloadService {
    List<DownloadInfo> getDownloadInfos(long packageId);
}