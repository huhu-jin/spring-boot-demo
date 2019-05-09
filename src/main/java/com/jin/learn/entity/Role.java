package com.jin.learn.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Role implements Serializable {
    private Long id;

    private String role;

    private String roleName;

    private Date createTime;

    private Date modifyTime;

    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;



}