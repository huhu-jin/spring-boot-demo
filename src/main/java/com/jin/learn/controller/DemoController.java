package com.jin.learn.controller;

import com.jin.learn.config.security.Authorize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    @GetMapping(name="asdf",value = {"asdf","aasdf","asdf/sdf/**"})
    public String hello() {
        return "world";
    }

    @GetMapping("/login2")
    @Authorize(value="reborn")
    public String hello1() {
        return "world";
    }

    @GetMapping("/login3")
    @Authorize("rebornreborn")
    public String hello3() {
        return "world";
    }

    @GetMapping("/free")
    public String test(@RequestParam String st) {
        log.error("ssss{}{}",123,2222);
        return "world"+st;

    }

}
