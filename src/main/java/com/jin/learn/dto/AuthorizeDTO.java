package com.jin.learn.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthorizeDTO {

    private String token;

    private Set<String> authorizes;
}
