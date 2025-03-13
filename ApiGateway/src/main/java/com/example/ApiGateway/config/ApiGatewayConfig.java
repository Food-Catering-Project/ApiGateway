package com.example.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                    .routes()
                .route(predicateSpec -> predicateSpec.path("/action/api/**") //or ("/**")
                        .uri("lb://ACTIONSERVICE"))
                .route(predicateSpec -> predicateSpec.path("/query/api/**")
                        .uri("lb://QUERYSERVICE"))

                .build();
    }

}

