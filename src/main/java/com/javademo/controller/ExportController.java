package com.javademo.controller;

import com.javademo.model.request.ExportRequest;
import com.javademo.service.ExportService;
import com.javademo.util.AsyncUtil;
import com.javademo.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * @Program: javademo
 * @Description: 导出数据，进度查询
 * @Author: zls
 * @Date: 2024-03-29 09:36
 **/
@Slf4j
@RestController
public class ExportController {

    @Autowired
    private ExportService exportService;

    /**
     * 导出数据
     */
    @PostMapping("/export")
    public JsonData exportData(@RequestBody ExportRequest exportRequest) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String key = "exportDateTest:" + format.format(new Date());
        AsyncUtil.submitTask(key, () -> exportService.exportData(exportRequest, key));
        return JsonData.buildSuccess(key);
    }

    /**
     * 查询导出进度
     */
    @GetMapping("/export/progress")
    public JsonData exportProgress(@RequestParam("key") String key){
        return JsonData.buildSuccess(exportService.getExportProgress(key));
    }
}
