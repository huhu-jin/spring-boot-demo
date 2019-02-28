package com.jin.learn.dto;

import com.jin.learn.exception.ExceptionCode;
import com.jin.learn.exception.SystemException;

public class ApiResponse {

    private int                 code;
    private String              msg;
    private Object              data;

    public ApiResponse(){

    }

    public ApiResponse(Integer code, String msg, Object data) {
       this.code =code;
       this.msg = msg;
       this.data = data;
    }


    public static ApiResponse OK() {
        return new ApiResponse(10000, "success", "");
    }

    public static ApiResponse OK(Object data) {
        return new ApiResponse(10000, "success", data);
    }

     public static ApiResponse ERROR(ExceptionCode exceptionCode) {
        return new ApiResponse(exceptionCode.getErrorCode(), exceptionCode.getErrorMsg(), "");
    }

    /**
     * 设置相应数据
     *
     * @param data
     */
    public void setResponseData(Object data) {
        if (data != null) {
            this.setData(data);
        } else {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setData(data);
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
