package com.atguigu.cloud.controller;

import com.atguigu.cloud.entity.Pay;
import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-13  00:36
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/pay")
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/add")
    @Operation(summary = "新增", description = "新增支付流水, 参数是JSON字符串")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        int i = payService.add(pay);
        return ResultData.success("成功插入记录,返回值:" + i);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水, 参数是Id")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        log.info("删除Pay，ID: {}", id);
        return ResultData.success(payService.delete(id));
    }

    @PutMapping("/update")
    @Operation(summary = "更新", description = "更新支付流水, 参数是JSON字符串, 根据Id更新")
    public ResultData<String> update(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return ResultData.success("成功修改记录,返回值:" + i);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "查询单个", description = "查询支付流水, 参数是Id")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        log.info("获取Pay，ID: {}", id);
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ResultData.success(payService.getById(id));
    }

    @GetMapping("/getAll")
    @Operation(summary = "查询所有", description = "查询所有支付流水")
    public ResultData<List<Pay>> getAll() {
        return ResultData.success(payService.getAll());
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/get/info")
    public String getInfoByConsul(@Value("${atguigu.info}") String info){
        return  "atguiguInfo:" + info + "\t" + "port:" + port;
    }

}
