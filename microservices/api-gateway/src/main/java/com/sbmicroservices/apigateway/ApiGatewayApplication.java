package com.sbmicroservices.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public WebFilter logRequestFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            // Log the incoming request details
            System.out.println("Incoming request: " + exchange.getRequest().getMethod() + " " + exchange.getRequest().getURI());
            HttpHeaders headers = exchange.getRequest().getHeaders();
            headers.forEach((header, values) -> System.out.println(header + ": " + values));

            // Continue processing the request
            return chain.filter(exchange);
        };
    }
}
