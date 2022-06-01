package com.example.demo2;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayRoutingConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/health/recovered/**").uri("http://localhost:8081")) //redirect it to the specific path using naming service and using the load balancer
                .route(p -> p.path("/health/verified/**").uri("http://localhost:8081")) //redirect it to the specific path using naming service and using the load balancer
                .route(p -> p.path("/population/country/**").uri("http://localhost:8082")) //redirect it to the specific path using naming service and using the load balancer
                .build();
    }
}

