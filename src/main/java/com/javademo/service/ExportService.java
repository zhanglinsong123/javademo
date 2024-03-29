package com.javademo.service;

import com.javademo.model.dto.RedisAsyncResultDto;
import com.javademo.model.request.ExportRequest;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-29 09:45
 **/
public interface ExportService {
    String exportData(ExportRequest exportRequest, String key);

    RedisAsyncResultDto getExportProgress(String key);
}
