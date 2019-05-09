package com.jin.learn.dto;

import com.jin.learn.exception.ExceptionCode;
import com.jin.learn.exception.SystemException;
import lombok.Data;

@Data
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

    public static ApiResponse ERROR(SystemException exception) {
        return ERROR(exception.getExceptionCode());
    }

    public void setResponseData(Object data) {
        if (data != null) {
            this.setData(data);
        } else {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setData(data);
        }
    }


}
