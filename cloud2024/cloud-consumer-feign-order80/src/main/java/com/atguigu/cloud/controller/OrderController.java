package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.enums.ReturnCodeEnum;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-14  20:04
 * @Description: TODO
 * @Version: 1.0
 */
@RequestMapping("/feign")
@RestController
@Slf4j
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    public ResultData delOrder(@PathVariable("id") Integer id) {
        return payFeignApi.delPay(id);
    }

    @PutMapping(value = "/pay/update")
    public ResultData updateOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.updatePay(payDTO);
    }

    @GetMapping(value = "/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        ResultData resultData = null;
        try {
            System.out.println("调用开始----:" + DateUtil.now());
            resultData = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束----:" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }

    @GetMapping(value = "/pay/getAll")
    public ResultData getPayAll() {
        return payFeignApi.getPayAll();
    }

    @GetMapping(value = "/pay/myLb")
    public String myLb() {
        return payFeignApi.myLb();
    }

}
