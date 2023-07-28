package com.jin.learn.service;

import java.io.IOException;

public interface BusinessService {

    String purchase(String userId, String commodityCode, int orderCount, int orderAmount) throws IOException;

}
