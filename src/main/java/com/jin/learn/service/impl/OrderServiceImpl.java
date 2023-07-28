package com.jin.learn.service.impl;

import com.jin.learn.dao.OrderTblMapper;
import com.jin.learn.entity.OrderTbl;
import com.jin.learn.service.OrderService;
import com.jin.learn.until.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderTblMapper orderMapper;

    @Override
    public OrderTbl create(String userId, String commodityCode, int orderCount, int money) throws IOException {
        if (orderCount > 10) {
            throw new RuntimeException("订单数量不能超过10");
        }
        OrderTbl orderInLocal = createOrderInLocal(userId, commodityCode, orderCount, money);
        log.info("create order in local success");
//        HttpClient.callAccountService(userId, money);
//        log.info("order service call account service to decrease money");

        return orderInLocal;
    }



    private OrderTbl createOrderInLocal(String userId, String commodityCode, int orderCount, int money) {
        OrderTbl orderTbl = new OrderTbl();
        orderTbl.setUserId(userId);
        orderTbl.setCount(orderCount);
        orderTbl.setMoney(money);
        orderTbl.setCommodityCode(commodityCode);
        orderMapper.insert(orderTbl);
        return orderTbl;
    }
}
