package com.example.train.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/loginForm")
    String login() {
        return "loginForm";
    }
}
