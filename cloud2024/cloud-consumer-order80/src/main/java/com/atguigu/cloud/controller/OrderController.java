package com.atguigu.cloud.controller;

import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-14  20:04
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    public static final String PaymentSrv_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    public ResultData delOrder(@PathVariable("id") Integer id) {
        return restTemplate.exchange(PaymentSrv_URL + "/pay/del/" + id, HttpMethod.DELETE, null, ResultData.class).getBody();
    }

    @PutMapping(value = "/pay/update")
    public ResultData updateOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.exchange(PaymentSrv_URL + "/pay/update", HttpMethod.PUT, new HttpEntity<>(payDTO), ResultData.class).getBody();
    }

    @GetMapping(value = "/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping("/pay/getAll")
    public ResultData getAllOrder() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/getAll", ResultData.class);
    }

    @GetMapping("/pay/get/info")
    private String getInfoByConsul() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }

    @GetMapping("/discovery")
    public String discovery() {
        for (String service : discoveryClient.getServices()) {
            System.out.println(service);
        }

        System.out.println("=======================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId() + element.getHost() + element.getUri());
        }
        return instances.get(0).getInstanceId() + ":" + instances.get(0).getPort();

    }

}
