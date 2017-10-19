package com.lacombe.promo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaRepositories(basePackages = "com.lacombe.promo3.registration.repository")
@EnableCircuitBreaker
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
//@EnableDiscoveryClient
@RestController
public class ApplicationSpring {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpring.class, args);
    }
}
