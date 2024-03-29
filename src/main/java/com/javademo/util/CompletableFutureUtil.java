package com.javademo.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @Program: javademo
 * @Description: 多线程通用工具类
 * @Author: zls
 * @Date: 2024-03-29 16:22
 **/
@Component
public class CompletableFutureUtil {
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    CompletableFutureUtil(@Qualifier("exportDataExecutor") ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    public <T, R> List<R> executeParallelTasks(Collection<T> list, Function<T, R> api, BiFunction<Throwable, T, R> exceptionHandler) {
        List<CompletableFuture<R>> collectFuture = list.stream()
                .map(s -> this.createFuture(
                        () -> api.apply(s),
                        e -> exceptionHandler.apply(e, s)))
                .collect(Collectors.toList());

        return collectFuture.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    public <T> CompletableFuture<T> createFuture(Supplier<T> logic, Function<Throwable, T> exceptionHandler) {
        return CompletableFuture.supplyAsync(logic, threadPoolTaskExecutor).exceptionally(exceptionHandler);
    }
}
