package com.javademo.aspect;

import com.javademo.annotation.RequestLimiting;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-27 17:30
 **/
@Aspect
@Slf4j
@Component
public class RequestLimitingAspect {
    @Resource
    private RedisTemplate redisTemplate;

    // 切点
    @Pointcut("@annotation(requestLimiting)")
    public void controllerAspect(RequestLimiting requestLimiting) {

    }

    // 环绕通知
    @Around("controllerAspect(requestLimiting)")
    public Object around(ProceedingJoinPoint joinPoint, RequestLimiting requestLimiting) throws Throwable {
        long period = requestLimiting.period();
        long limitCount = requestLimiting.count();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String key = "req_limiting_".concat(uri).concat(ip);

        ZSetOperations<String, Long> zSetOperations = redisTemplate.opsForZSet();
        // 获取当前时间
        long currentMs = System.currentTimeMillis();
        // 添加当前时间
        zSetOperations.add(key, currentMs, currentMs);
        // 设置过期时间
        redisTemplate.expire(key, period, TimeUnit.SECONDS);
        // 删除出窗口之外的值
        zSetOperations.removeRangeByScore(key, 0, currentMs - period * 1000);
        // 查询访问次数
        Long count = zSetOperations.zCard(key);
        if (count > limitCount) {
            log.warn("用户IP[" + ip + "]访问地址[" + uri + "]超过了限定的次数[" + limitCount + "]");
            throw new RuntimeException("请求过于频繁，请稍后再试");
        }

        return joinPoint.proceed();

    }
}
