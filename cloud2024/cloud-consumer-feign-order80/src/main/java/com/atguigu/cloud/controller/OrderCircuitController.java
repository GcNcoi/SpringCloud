package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.resp.ResultData;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-16  23:15
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/feign")
public class OrderCircuitController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/pay/circuit/get/{id}")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    // 服务器降级后的兜底处理方法
    public String myCircuitFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "myCircuitFallback, 系统繁忙, 请稍后再试...";
    }

    // (船的)舱壁、隔离
    @GetMapping(value = "/pay/bulkhead/get/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    // 舱壁、隔离后的兜底处理方法
    public String myBulkheadFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return "myBulkheadFallback, 系统繁忙, 请稍后再试...";
    }

    // (船的)舱壁、隔离 threadPool
    @GetMapping(value = "/pay/bulkheadPool/get/{id}")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<String> myBulkheadPool(@PathVariable("id") Integer id) {
        try {
            System.out.printf("-----开始进入" + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("-----准备离开" + Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + "Bulkhead.Type.THREADPOOL");
    }

    // 舱壁、隔离后的兜底处理方法
    public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable t) {
        // 这里是容错处理逻辑，返回备用结果
        return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL, 系统繁忙, 请稍后再试...");
    }

    @GetMapping("/pay/rateLimit/get/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "fallback4RateLimit")
    public String getPayById4RateLimit(@PathVariable("id") Integer id) {
        return payFeignApi.myRateLimit(id);
    }

    public String fallback4RateLimit(Throwable throwable) {
        return "服务器限流, 请稍后重试...";
    }

}
