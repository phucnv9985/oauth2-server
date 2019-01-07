package com.example.oauth2server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

//    @Autowired
//    private UserDetailsService userService;

    @GetMapping("/")
    public String greeting() {
        return "Spring Security In-memory Authentication Example";
    }

}
