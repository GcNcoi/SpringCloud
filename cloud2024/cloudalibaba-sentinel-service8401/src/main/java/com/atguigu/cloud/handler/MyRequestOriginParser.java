package com.atguigu.cloud.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.handler
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-07-05  17:21
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class MyRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getParameter("serverName");
    }

}
