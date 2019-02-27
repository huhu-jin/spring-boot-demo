package com.jin.learn.request.authorize;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
