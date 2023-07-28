package com.jin.learn.request.authorize;


import lombok.Data;

@Data
public class BusinessOrder {

    private String userId;

    private String commodityCode;

    private int orderCount;

    private int orderAmount;

}
