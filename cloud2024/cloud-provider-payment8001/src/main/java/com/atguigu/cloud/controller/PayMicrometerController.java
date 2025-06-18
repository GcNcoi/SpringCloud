package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-18  23:40
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/pay")
public class PayMicrometerController {

    @GetMapping("/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return "这是链路追踪, Id:" + IdUtil.simpleUUID();
    }

}
