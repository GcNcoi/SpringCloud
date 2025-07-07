package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.api.model.v2.Result;
import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.enums.ReturnCodeEnum;
import com.atguigu.cloud.resp.ResultData;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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

    // openfeign + sentinel 进行服务降级和流量监控的整合处理case
    @GetMapping("/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public ResultData<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        // 模拟查询
        PayDTO payDTO = new PayDTO(1024, orderNo, "pay" + IdUtil.simpleUUID(), 1, BigDecimal.valueOf(9.9));
        return ResultData.success(payDTO);
    }

    public ResultData<PayDTO> handlerBlockHandler(String orderNo, BlockException e) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "服务提供者:触发sentinel流控配置规则保护");
    }

}
