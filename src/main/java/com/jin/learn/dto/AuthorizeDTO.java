package com.jin.learn.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@Builder
public class AuthorizeDTO {

    private String token;

    private Collection<? extends GrantedAuthority> authorizes;
}
