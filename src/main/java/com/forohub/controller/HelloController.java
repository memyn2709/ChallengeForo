package com.forohub.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping()
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/kitty")
    public String kitty() {
        return "<html><body><h1>Hello, Kitty!</h1><img src='/img/sidharta.jpeg' width='50%' alt='Kitty'></body></html>";
    }
}
