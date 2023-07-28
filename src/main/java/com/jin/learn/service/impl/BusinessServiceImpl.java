package com.jin.learn.service.impl;

import com.jin.learn.service.BusinessService;
import com.jin.learn.until.HttpClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Override
    @GlobalTransactional
    public String purchase(String userId, String commodityCode, int orderCount, int orderAmount) throws IOException {
        // 减库存
        String status = HttpClient.callStorageService(commodityCode, orderCount);
        log.info("storage service call status:{}", status);


        // 下订单
         status = HttpClient.callOrderService(userId, commodityCode, orderCount, orderAmount);
        log.info("order service call status:{}", status);


        return "success";
    }
}
