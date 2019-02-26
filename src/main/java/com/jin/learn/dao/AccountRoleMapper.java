package com.jin.learn.dao;

import com.jin.learn.entity.AccountRole;

public interface AccountRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AccountRole record);

    int insertSelective(AccountRole record);

    AccountRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountRole record);

    int updateByPrimaryKey(AccountRole record);
}