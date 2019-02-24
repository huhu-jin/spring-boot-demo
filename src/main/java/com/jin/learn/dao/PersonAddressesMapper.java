package com.jin.learn.dao;

import com.jin.learn.entity.PersonAddresses;

public interface PersonAddressesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonAddresses record);

    int insertSelective(PersonAddresses record);

    PersonAddresses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonAddresses record);

    int updateByPrimaryKey(PersonAddresses record);
}