package com.jin.learn.service.impl;

import com.jin.learn.dao.StorageTblMapper;
import com.jin.learn.entity.StorageTbl;
import com.jin.learn.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {


    @Autowired
    private StorageTblMapper storageTblMapper;



    @Override
    public void deduct(String commodityCode, int count) {
        StorageTbl storageTbl = new StorageTbl();
        storageTbl.setCommodityCode(commodityCode);
        storageTbl.setCount(count);
        storageTblMapper.deductCountByCommodityCode(storageTbl);
    }
}
