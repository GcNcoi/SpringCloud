package com.atguigu.cloud.resp;

import com.atguigu.cloud.enums.ReturnCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.resp
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-14  19:14
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    private String code;

    private String message;

    private T data;

    private Long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData resultData = new ResultData();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);

        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);

        return resultData;
    }

}
