package com.lacombe.promo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCircuitBreaker
@EnableJpaRepositories(basePackages = "com.lacombe.promo3.registration.repository")
@EnableAutoConfiguration
@SpringBootApplication
public class ApplicationSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringBoot.class, args);
    }
}

