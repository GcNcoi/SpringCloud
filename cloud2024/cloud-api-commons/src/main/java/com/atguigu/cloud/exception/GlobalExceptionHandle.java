package com.atguigu.cloud.exception;

import com.atguigu.cloud.enums.ReturnCodeEnum;
import com.atguigu.cloud.resp.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.exception
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-14  19:34
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
//@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e){

        log.error("全局异常信息:{}", e.getMessage(), e);
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());

    }

}
