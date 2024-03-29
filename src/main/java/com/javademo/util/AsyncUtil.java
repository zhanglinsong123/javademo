package com.javademo.util;

import com.alibaba.fastjson.JSON;
import com.javademo.model.dto.RedisAsyncResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-29 09:50
 **/
@Slf4j
@Component
public class AsyncUtil {

    private static RedisTemplate<String, Object> redisTemplate;

    private static ThreadPoolTaskExecutor executor;

    public AsyncUtil(RedisTemplate<String, Object> redisTemplate, @Qualifier("exportDataExecutor") ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.redisTemplate = redisTemplate;
        this.executor = threadPoolTaskExecutor;
    }

    public static void setTotal(String key, Integer total) {
        RedisAsyncResultDto result = getResult(key);
        Optional.ofNullable(result).ifPresent(re -> {
            re.setTotal(total);
            saveResult(key, result);
        });
    }

    private static void saveResult(String key, RedisAsyncResultDto result) {
        setToRedis(key, result);
    }

    private static void setToRedis(String key, RedisAsyncResultDto result) {
        redisTemplate.opsForValue().set(key, result, 5, TimeUnit.MINUTES);
    }

    public static RedisAsyncResultDto getResult(String key) {
        return JSON.parseObject(JSON.toJSONString(redisTemplate.opsForValue().get(key)), RedisAsyncResultDto.class);
    }

    public static void setDone(String key, Integer done) {
        RedisAsyncResultDto result = getResult(key);
        Optional.ofNullable(result).ifPresent(re -> {
            re.setDone(done);
            saveResult(key, result);
        });
    }

    public static RedisAsyncResultDto submitTask(String key, Supplier<String> supplier) {
        AtomicReference<RedisAsyncResultDto> re = new AtomicReference<>(new RedisAsyncResultDto());
        //初始化数据到redis
        re.get().setSuccess(false);
        re.get().setKey(key);
        re.get().setDone(0);
        re.get().setTotal(0);
        setToRedis(key, re.get());


        executor.execute(() -> {
            String msg = null;
            try {
                //这里去执行导出的任务
                String url = supplier.get();
                RedisAsyncResultDto result = getResult(key);
                if (null != result) {
                    re.set(result);
                }
                re.get().setUrl(url);
                re.get().setFlag(true);
            } catch (Exception e) {
                re.get().setFlag(false);
                msg = e.getMessage();
                log.error("submitTask error", e);
            }
            re.get().setSuccess(true);
            re.get().setMsg(msg);
            saveResult(key, re.get());
        });
        return re.get();
    }
}
