package com.jin.learn.dao;

import com.jin.learn.entity.StorageTbl;

public interface StorageTblMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StorageTbl record);

    int insertSelective(StorageTbl record);

    StorageTbl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StorageTbl record);

    int updateByPrimaryKey(StorageTbl record);


    int deductCountByCommodityCode(StorageTbl record);
}