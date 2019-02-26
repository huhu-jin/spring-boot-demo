package com.jin.learn.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


public class LoginController {

    @PostMapping
    public ResponseEntity login(){
        // 校验参数登录
         jwtTokenUtil.generateToken(userDetails);
    }
}
