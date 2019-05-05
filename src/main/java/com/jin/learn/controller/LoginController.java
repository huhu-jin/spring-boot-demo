package com.jin.learn.controller;

import com.jin.learn.dto.ApiResponse;
import com.jin.learn.dto.AuthorizeDTO;
import com.jin.learn.entity.Account;
import com.jin.learn.request.authorize.LoginRequest;
import com.jin.learn.until.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/demo/authorize")
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/login")
    public ApiResponse login(@RequestBody @Valid LoginRequest request){
        Account account = (Account)userDetailsService.loadUserByUsername(request.getUsername());
        if (account == null || !bCryptPasswordEncoder.matches(request.getPassword(),account.getPassword())) {
            return new ApiResponse(1001, "用户名密码错误", "");
        }
        String token =  JWTUtil.doGenerateToken(account);
        AuthorizeDTO authorizeDTO = AuthorizeDTO.builder().authorizes(account.getAuthorities()).token(token).build();
        return ApiResponse.OK(authorizeDTO);
    }

}
