package com.jin.learn.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class AccountRole implements Serializable {
    private Long id;

    private Long accountId;

    private Long roleId;

    private static final long serialVersionUID = 1L;


}