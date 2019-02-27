package com.jin.learn.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/login")
    public String hello() {
        return "world";
    }

    @GetMapping("/login2")
    @PreAuthorize("hasAuthority('reborn')")
    public String hello1() {
        return "world";
    }

    @GetMapping("/login3")
    @PreAuthorize("hasAuthority('rebornreborn')")
    public String hello3() {
        return "world";
    }




    @GetMapping("/free")
    public String test() {
        return "world";
    }
}
