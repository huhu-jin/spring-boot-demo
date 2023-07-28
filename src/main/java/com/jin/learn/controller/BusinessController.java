package com.jin.learn.controller;

import com.jin.learn.request.authorize.BusinessOrder;
import com.jin.learn.service.BusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("business")
@Slf4j
public class BusinessController {


    @Autowired
    private BusinessService businessService;
    //总入口

    @PostMapping
    public String createBusinessOrder(@RequestBody BusinessOrder businessOrder) throws IOException {
        // call order service
        businessService.purchase(businessOrder.getUserId(), businessOrder.getCommodityCode(), businessOrder.getOrderCount(), businessOrder.getOrderAmount());
        return "success";
    }



}
