package com.atguigu.cloud.controller;

import com.atguigu.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-07-02  23:40
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
public class FlowLimitController {

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "--------testB";
    }

    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "--------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "--------testD";
    }

    /* 流控效果的排队等待 */
    @GetMapping("/testE")
    public String testE() {
        flowLimitService.common();
        return "--------testE";
    }

    /*
    * 新增熔断规则-慢调用比例
    *  */
    @GetMapping("/testF")
    public String testF() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "--------testF 新增熔断规则-慢调用比例 ";
    }

    /*
     * 新增熔断规则-异常比例
     *  */
    @GetMapping("/testG")
    public String testG() {
        int age = 10/0;
        return "--------testG 新增熔断规则-异常比例 ";
    }

    /*
     * 新增熔断规则-异常数
     *  */
    @GetMapping("/testH")
    public String testH() {
        int age = 10/0;
        return "--------testH 新增熔断规则-异常数 ";
    }

}
