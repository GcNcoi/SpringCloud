package com.atguigu.cloud.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.config
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-14  20:03
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
@LoadBalancerClient(value = "cloud-payment-service", configuration = RestTemplateConfig.class)
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * @description: 修改微服务轮询为随机
     * @author: GuoXiaofeng
     * @date: 2025/6/15 18:11
     * @param: [environment, loadBalancerClientFactory]
     * @return: org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer<org.springframework.cloud.client.ServiceInstance>
     **/
//    @Bean
//    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
//                                                            LoadBalancerClientFactory loadBalancerClientFactory) {
//        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
//    }

}
