package com.lacombe.promo3.registration.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LunchService {

    @HystrixCommand(fallbackMethod = "defaultLunchMessage")
    public String getLunch(String userEmail) {
        return new RestTemplate()
                .getForObject("http://localhost:8888/lunch/{userEmail}",
                        String.class, userEmail);
    }

    private String defaultLunchMessage(String userEmail) {
        return "The service is not available";
    }
}
