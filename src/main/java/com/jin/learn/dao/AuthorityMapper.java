package com.jin.learn.dao;

import com.jin.learn.entity.Authority;

import java.util.List;
import java.util.Set;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    Set<String> findByAccountId(Long id);

}