package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entity.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.service.impl
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-07-10  23:25
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource //订单微服务通过OpenFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;

    @Resource //订单微服务通过OpenFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "zzyy-create-order",rollbackFor = Exception.class) //AT
    public void create(Order order) {
        String xid = RootContext.getXID();
        // 1.新建订单
        log.info("--------------开始新建订单:" + "\t" + "xid:" + xid);
        // 订单新建时默认初始订单式零
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        Order orderFromDB = null;
        if (result > 0) {
            // 从mysql里面查出刚插入的记录
            orderFromDB = orderMapper.selectOne(order);
            log.info("--------------新建订单成功，orderFromDB info:" + orderFromDB);
            // 2.扣减库存
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("-------> 订单微服务结束调用Storage库存，做扣减完成");
            //3. 扣减账号余额
            log.info("-------> 订单微服务开始调用Account账号，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("-------> 订单微服务结束调用Account账号，做扣减完成");
            //4. 修改订单状态
            //订单状态status：0：创建中；1：已完结
            log.info("-------> 修改订单状态");
            orderFromDB.setStatus(1);

            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);

            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);

            log.info("-------> 修改订单状态完成" + "\t" + updateResult);
            log.info("-------> orderFromDB info: " + orderFromDB);
        }
        log.info("--------------结束新建订单:" + "\t" + "xid:" + xid);
    }

}
