package com.example.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


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
//                .route(predicateSpec -> predicateSpec.path("/authenticate/api/auth/**")
//                        .uri("lb://AUTHENTICATIONSERVICE"))
                .route(predicateSpec -> predicateSpec.path("/cart/api/**")
                        .uri("lb://CARTSERVICE"))

                .build();
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:5173"); // Change this for production
        corsConfig.addAllowedMethod(HttpMethod.GET);
        corsConfig.addAllowedMethod(HttpMethod.POST);
        corsConfig.addAllowedMethod(HttpMethod.PUT);
        corsConfig.addAllowedMethod(HttpMethod.DELETE);
        corsConfig.addAllowedHeader("*");
          corsConfig.setAllowCredentials(false); // No JWT or cookies, so no need for credentials


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

//    @Bean
//    public CorsWebFilter corsFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.addAllowedOrigin("http://localhost:5173"); // Your frontend URL
//        corsConfig.addAllowedMethod("*"); // Allows GET, POST, PUT, DELETE
//        corsConfig.addAllowedHeader("*"); // Allows all headers
//        corsConfig.setAllowCredentials(false); // No JWT or cookies, so no need for credentials
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return new CorsWebFilter(source);
//    }


}


