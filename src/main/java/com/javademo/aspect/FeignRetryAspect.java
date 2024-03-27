package com.javademo.aspect;

import com.javademo.annotation.FeignRetry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * @Program: javademo
 * @Description:
 * @Author: zls
 * @Date: 2024-03-27 17:06
 **/
@Aspect
@Slf4j
@Component
public class FeignRetryAspect {

    @Pointcut("@annotation(feignRetry)")
    public void feignRetryAspect(FeignRetry feignRetry) {

    }

    @Around("feignRetryAspect(feignRetry)")
    public Object retry(ProceedingJoinPoint joinPoint, FeignRetry feignRetry) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(prepareBackOffPolicy(feignRetry));
        retryTemplate.setRetryPolicy(prepareSimpleRetryPolicy(feignRetry));
        return retryTemplate.execute(context -> {
                    int retryCount = context.getRetryCount();
                    log.warn("请求重试method: {}, max attempt: {}, delay: {}, retry count: {}",
                            signature.getName(),
                            feignRetry.maxAttempts(),
                            feignRetry.backOff().delay(),
                            retryCount
                    );

                    return joinPoint.proceed(joinPoint.getArgs());
                },
                retryContext -> {
                    //重试失败后执行的代码
                    log.error("我在重试了{}次后，最终还是失败了======", retryContext.getRetryCount());
                    return "failed callback";
                }
        );
    }

    private BackOffPolicy prepareBackOffPolicy(FeignRetry feignRetry) {
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();

        // Set initial interval
        backOffPolicy.setInitialInterval(feignRetry.backOff().delay());

        // Set maximum interval
        backOffPolicy.setMaxInterval(feignRetry.backOff().maxDelay());

        // Set multiplier
        backOffPolicy.setMultiplier(feignRetry.backOff().multiplier());

        return backOffPolicy;
    }

    private SimpleRetryPolicy prepareSimpleRetryPolicy(FeignRetry feignRetry) {
        // Create a SimpleRetryPolicy with the maxAttempts specified in FeignRetry
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(feignRetry.maxAttempts());

        return retryPolicy;
    }




}
