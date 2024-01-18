package com.afrikatek.catalogservice.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String getGreeting() {
        return "Welcome to mumu's book corner";
    }
}
