package com.jin.learn.service;

import com.jin.learn.entity.OrderTbl;

import java.io.IOException;

public interface OrderService {

    OrderTbl create(String userId, String commodityCode, int orderCount, int money) throws IOException;


}
