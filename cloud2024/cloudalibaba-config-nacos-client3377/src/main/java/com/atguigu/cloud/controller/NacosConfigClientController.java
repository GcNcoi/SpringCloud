package com.atguigu.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-25  23:53
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RefreshScope
public class NacosConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }

}
