package com.atguigu.cloud.apis;

import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient(value = "cloud-payment-service")
// 通过gateway访问，需要打开gateway微服务
@FeignClient(value = "cloud-gateway")
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

    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/rateLimit/get/{id}")
    String myRateLimit(@PathVariable("id") Integer id);

    @GetMapping("/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    @GetMapping("/pay/gateway/get/{id}")
    ResultData<PayDTO> getGateWayById(@PathVariable("id") Integer id);

    @GetMapping("/pay/gateway/getInfo")
    ResultData<String> getGateWayInfo();

}
