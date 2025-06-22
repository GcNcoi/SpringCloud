package com.atguigu.cloud.mygateway;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @BelongsProject: cloud2024
 * @BelongsPackage: com.atguigu.cloud.mygateway
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-06-22  23:02
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userLevel = serverWebExchange.getRequest().getQueryParams().getFirst("userLevel");
                if (userLevel == null) return false;
                if (userLevel.equals(config.getUserLevel())) return true;
                return false;
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userLevel");
    }

    @Validated
    public static class Config {
        @Setter
        @Getter
        @NotEmpty
        private String userLevel;
    }

}
