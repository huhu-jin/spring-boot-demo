package com.jin.learn.controller;

import com.jin.learn.config.security.Authorize;
import com.jin.learn.dto.ApiResponse;
import com.jin.learn.entity.OrderTbl;
import com.jin.learn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping
    public ApiResponse createOrder(@RequestBody OrderTbl order) throws IOException {
        log.info("start to create the order code: {}", order.getCommodityCode());
        orderService.create(order.getUserId(), order.getCommodityCode(), order.getCount(), order.getMoney());
        log.info("finish to create the order code: {}", order.getCommodityCode());
        return ApiResponse.OK();
    }



}
