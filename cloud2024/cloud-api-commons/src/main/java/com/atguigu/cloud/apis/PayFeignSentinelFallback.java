package com.atguigu.cloud.apis;

import com.atguigu.cloud.entity.PayDTO;
import com.atguigu.cloud.enums.ReturnCodeEnum;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.apis
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-07-07  23:30
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class PayFeignSentinelFallback implements PayFeignSentinelApi{

    @Override
    public ResultData<PayDTO> getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，FallBack服务降级api-common统一调用方法...");
    }

}
