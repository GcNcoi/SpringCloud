package com.atguigu.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.service
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-07-02  23:55
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Service
public class FlowLimitService {

    @SentinelResource("common")
    public void common() {
        log.info("into ... common ...");
    }

}