package com.atguigu.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-07-05  11:27
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/rateLimit")
@Slf4j
public class RateLimitController {

    @GetMapping("/byUrl")
    public String byUrl() {
        return "按rest地址限流测试";
    }

    @GetMapping("/byResource")
    @SentinelResource(value = "byResourceSentinelResource", blockHandler = "handleBlockHandler")
    public String byResource() {
        return "按资源名称SentinelResource限流测试";
    }

    public String handleBlockHandler(BlockException blockException) {
        return "服务暂时不可用，触发@SentinelResource，请稍后再试...";
    }

    @GetMapping("/doAction/{p1}")
    @SentinelResource(value = "doActionSentinelResource", blockHandler = "doActionBlockHandler", fallback = "doActionFallback")
    public String doAction(@PathVariable("p1") Integer p1) {
        if (p1 == 0) {
            throw new RuntimeException();
        }
        return "使用注解并使用doAction, Fallback";
    }

    public String doActionBlockHandler(@PathVariable("p1") Integer p1, BlockException e) {
        log.error("sentinel配置自定义限流:{}", e.getMessage());
        return "服务不可用, 这是自定义返回的字符串，请稍后再试...";
    }

    public String doActionFallback(@PathVariable("p1") Integer p1, Throwable e) {
        log.error("程序逻辑异常:{}", e.getMessage());
        return "逻辑异常, 这是自定义返回的字符串" + e.getMessage();
    }

    /*
    * 热点参数限流
    *  */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "testHotKeyBlockHandler")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey ...";
    }

    public String testHotKeyBlockHandler(@RequestParam(value = "p1", required = false) String p1,
                                         @RequestParam(value = "p2", required = false) String p2, BlockException blockException) {
        return "testHotKey blockException ...";
    }
}
