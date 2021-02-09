package com.jin.learn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayloadDto {
    private String sub;
    private Long iat;
    private Long exp;
    private String jti;
    private String username;
    private List<String> authorities;
}
