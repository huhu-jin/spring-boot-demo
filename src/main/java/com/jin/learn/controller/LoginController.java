package com.jin.learn.controller;

import com.jin.learn.dto.ApiResponse;
import com.jin.learn.dto.AuthorizeDTO;
import com.jin.learn.entity.Account;
import com.jin.learn.request.authorize.LoginRequest;
import com.jin.learn.service.AccountService;
import com.jin.learn.service.AuthorizationService;
import com.jin.learn.until.JWTUtil;
import com.jin.learn.until.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/demo/authorize")
@RequiredArgsConstructor
public class LoginController {

    private final AccountService accountService;

    private final AuthorizationService authorizationService;


    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ApiResponse login(@RequestBody @Valid LoginRequest request){
        Account account = accountService.findByUsername(request.getUsername());
        if (account == null || !bCryptPasswordEncoder.matches(request.getPassword(),account.getPassword())) {
            return new ApiResponse(1001, "用户名密码错误", "");
        }
        String token =  JWTUtil.doGenerateToken(account);
        Set<String> authorization = authorizationService.findByAccountId(account.getId());
        AuthorizeDTO authorizeDTO = AuthorizeDTO.builder().authorizes(authorization).token(token).build();
        return ApiResponse.OK(authorizeDTO);
    }

}
