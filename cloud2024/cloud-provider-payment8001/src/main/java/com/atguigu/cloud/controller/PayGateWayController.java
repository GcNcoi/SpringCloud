package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.controller
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-19  23:58
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/pay/gateway")
public class PayGateWayController {

    @Resource
    private PayService payService;

    @GetMapping("/get/{id}")
    @Operation(summary = "查询单个", description = "查询支付流水, 参数是Id")
    public ResultData<PayDTO> getById(@PathVariable("id") Integer id) {
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(payService.getById(id), payDTO);
        return ResultData.success(payDTO);
    }

    @GetMapping("/getInfo")
    public ResultData<String> getInfo() {
        return ResultData.success("这是网关测试: " + IdUtil.simpleUUID());
    }

    @GetMapping("/filter")
    public ResultData<String> getFilter(HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);
            log.info("请求头: {}, 请求值: {}", headerName, headerValue);
            if (headerName.equalsIgnoreCase("X-Request-Ggg")) {
                result.append(headerName).append("\t").append(headerValue);
            }
        }
        System.out.println("=========================");
        String customerId = request.getParameter("customerId");
        System.out.println("request Parameter customerId: " + customerId);

        String customerName = request.getParameter("customerName");
        System.out.println("request Parameter customerName: " + customerName);
        System.out.println("=========================");
        return ResultData.success(result + "\t ID: " + IdUtil.simpleUUID());
    }

}
