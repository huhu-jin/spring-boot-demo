package com.jin.learn.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Authority implements Serializable {
    private Long id;

    private Integer type;

    private Long pid;

    private String authority;

    private String authorityName;

    private Date createTime;

    private Date modifyTime;

    private Boolean isDeleted;

    private static final long serialVersionUID = 1L;

}