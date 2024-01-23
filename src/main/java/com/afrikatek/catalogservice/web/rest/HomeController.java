package com.afrikatek.catalogservice.web.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${polar.greeting}")
    private String message;
    @GetMapping("/")
    public String getGreeting() {
        return message;
    }
}
