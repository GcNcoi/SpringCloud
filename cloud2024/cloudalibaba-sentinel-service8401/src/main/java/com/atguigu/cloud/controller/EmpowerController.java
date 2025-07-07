package com.atguigu.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-07-05  17:18
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestController
public class EmpowerController {

    @GetMapping("/empower")
    public String requestSentinel() {
        log.info("测试授权规则");
        return "测试授权规则";
    }

}
