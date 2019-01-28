package com.zxa.producers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProducersApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducersApplication.class,args);
    }
}
