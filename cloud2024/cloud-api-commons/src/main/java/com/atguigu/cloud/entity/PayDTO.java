package com.atguigu.cloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.entities
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-12  23:53
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO implements Serializable {

    private Integer id;

    private String payNo;

    private String orderNo;

    private Integer userId;

    private BigDecimal amount;

}
