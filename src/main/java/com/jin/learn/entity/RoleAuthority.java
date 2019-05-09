package com.jin.learn.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class RoleAuthority implements Serializable {
    private Long id;

    private Long roleId;

    private Long authorityId;

    private static final long serialVersionUID = 1L;

}