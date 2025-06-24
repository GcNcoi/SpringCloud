package com.atguigu.cloud.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-24  23:39
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/pay")
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id) {
        return "nacos registry.serverPort: " + serverPort + ",id: " + id;
    }

}
