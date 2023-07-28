package com.jin.learn.until;

import com.alibaba.fastjson.JSON;
import io.seata.integration.http.DefaultHttpExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
public class HttpClient {


    public static DefaultHttpExecutor getClient(){
        return DefaultHttpExecutor.getInstance();
    }


    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }


    // 通过 http call business controller
    public static String callOrderService(String userId, String commodityCode, int orderCount, int orderAmount) throws IOException {

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", userId);
        requestBody.put("commodityCode", commodityCode);
        requestBody.put("count", orderCount);
        requestBody.put("money", orderAmount);

        HttpResponse response = getClient().executePost("http://127.0.0.1:8081", "/order", toJson(requestBody), HttpResponse.class);
        if (response.getStatusLine().getStatusCode()>=200 && response.getStatusLine().getStatusCode()<300) {
            return "success";
        }else {
            throw new RuntimeException("create order 失败");
        }
    }

    // 减少库存
    public static String callStorageService(String commodityCode, int count) throws IOException {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("commodityCode", commodityCode);
        requestBody.put("count", count);

        HttpResponse response = getClient().executePost("http://127.0.0.1:8082", "/storage/deduct", toJson(requestBody), HttpResponse.class);
        if (response.getStatusLine().getStatusCode()>=200 && response.getStatusLine().getStatusCode()<300) {
            return "success";
        }else {
            throw new RuntimeException("减少库存 失败");
        }
    }

    // 用户账户扣钱
    public static String callAccountService(String userId, int money) throws IOException {

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", userId);
        requestBody.put("money", money);

        HttpResponse response = getClient().executePost("http://127.0.0.1:8083", "/account/debit", toJson(requestBody), HttpResponse.class);
        if (response.getStatusLine().getStatusCode()>=200 && response.getStatusLine().getStatusCode()<300) {
            return "success";
        }else {
            throw new RuntimeException("用户账户扣钱 失败");
        }
    }


}
