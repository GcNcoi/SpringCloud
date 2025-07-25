package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-16  23:04
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
public class PayCircuitController {

    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("----circuit id 不能为负数");

        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return "Hello,circuit! inputId:" + id + "\t" + IdUtil.simpleUUID();
    }

    /* Resilience4j bulkhead 的例子 */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id) {
        if (id < 0) throw new RuntimeException("----bulkhead id 不能为负数");

        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return "Hello,bulkhead! inputId:" + id + "\t" + IdUtil.simpleUUID();
    }

    // Resilience4j rateLimit 例子
    @GetMapping(value = "/pay/rateLimit/get/{id}")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return "Hello,myRateLimit! inputId:" + id + "\t" + IdUtil.simpleUUID();
    }

}
