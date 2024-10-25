package com.example.ecom.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("/")
    public String Home() {
        return "<h1>Hi, This is a initial render page for demo.!</h1>";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
