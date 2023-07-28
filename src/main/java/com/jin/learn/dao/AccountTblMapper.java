package com.jin.learn.dao;

import com.jin.learn.entity.AccountTbl;

public interface AccountTblMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountTbl record);

    int insertSelective(AccountTbl record);

    AccountTbl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountTbl record);

    int updateByPrimaryKey(AccountTbl record);

    void deductMoneyByUserId(AccountTbl record);
}