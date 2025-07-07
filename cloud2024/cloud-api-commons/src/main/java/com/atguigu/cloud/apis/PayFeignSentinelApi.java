package com.atguigu.cloud.apis;

import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PayFeignSentinelFallback.class)
public interface PayFeignSentinelApi {

    @GetMapping("/pay/nacos/get/{orderNo}")
    public ResultData<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo);

}