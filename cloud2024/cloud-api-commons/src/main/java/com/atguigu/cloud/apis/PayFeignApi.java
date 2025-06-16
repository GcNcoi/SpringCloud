package com.atguigu.cloud.apis;

import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {

    @PostMapping(value = "/pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    @DeleteMapping(value = "/pay/del/{id}")
    ResultData delPay(@PathVariable("id") Integer id);

    @PutMapping(value = "/pay/update")
    ResultData updatePay(@RequestBody PayDTO payDTO);

    @GetMapping(value = "/pay/get/{id}")
    ResultData getPayInfo(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/getAll")
    ResultData getPayAll();

    @GetMapping(value = "/pay/get/info")
    String myLb();

    @GetMapping("/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

}
