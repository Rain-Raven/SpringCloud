package com.zxa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = {"com.zxa"})
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class ControlCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ControlCenterApplication.class,args);
    }
}
