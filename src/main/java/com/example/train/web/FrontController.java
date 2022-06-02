package com.example.train.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
