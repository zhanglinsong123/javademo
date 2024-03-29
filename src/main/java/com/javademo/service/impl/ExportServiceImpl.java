package com.javademo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javademo.execption.JavaDemoException;
import com.javademo.mapper.TestMapper;
import com.javademo.model.dto.RedisAsyncResultDto;
import com.javademo.model.request.ExportRequest;
import com.javademo.service.ExportService;
import com.javademo.util.AsyncUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-29 09:46
 **/
@Service
@Slf4j
public class ExportServiceImpl implements ExportService {

    private final TestMapper testMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    public ExportServiceImpl(TestMapper testMapper, RedisTemplate<String, Object> redisTemplate) {
        this.testMapper = testMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String exportData(ExportRequest exportRequest, String key) {
        List<Map<String, Object>> list = testMapper.queryAll();
        AtomicInteger done = new AtomicInteger();
        AsyncUtil.setTotal(key, list.size());
        list.forEach(map -> {
            //数据转换，模拟耗时操作
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("exportData error", e);
            }
            done.getAndIncrement();
            AsyncUtil.setDone(key, done.get());
            log.info("Executing export task in thread: " + Thread.currentThread().getName());
        });
        // 组织到处数据
        Map<String, Object> map = new HashMap<>();
        map.put("w", key);
        // 生产excel文件 对象oss存储 返回url
        return getExcelUrl(map);
    }

    @Override
    public RedisAsyncResultDto getExportProgress(String key) {
        try {
            return AsyncUtil.getResult(key);
        } catch (Exception e) {
            log.error("getExportProgress error", e);
            throw new JavaDemoException(1001, "获取导出进度失败，可能是key不存在！");
        }
    }

    /**
     * 生产excel文件 对象oss存储 返回url
     * @param map
     * @return
     */
    private String getExcelUrl(Map<String, Object> map) {
        // todo 生产excel文件 对象oss存储 返回url
        return "我是oss url地址";
    }
}
