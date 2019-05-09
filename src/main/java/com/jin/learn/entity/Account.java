package com.jin.learn.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户类必须实现userDetail接口 返回权限
@Data
public class Account implements Serializable {

    private Long id;

    private String username;

    private String password;

    private Boolean status;

    private Integer type;

    private String nickname;

    private String email;

    private String mobile;

    private String department;

    private Date createTime;

    private Date modifyTime;

    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

}