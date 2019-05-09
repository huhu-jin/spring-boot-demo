package com.jin.learn.dao;

import com.jin.learn.entity.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    Account selectByUsername(String username);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);


}