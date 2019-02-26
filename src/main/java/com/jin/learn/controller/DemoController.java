package com.jin.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/login")
    public String hello() {
        return "world";
    }


    @GetMapping("/test")
    public String test() {
        return "world";
    }
}
