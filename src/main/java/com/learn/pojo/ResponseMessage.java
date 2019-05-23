package com.learn.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseMessage{

    public ResponseMessage(Object data, String status, String errorinfo) {
        this.data = data;
        this.status = status;
        this.errorinfo = errorinfo;
    }

    public ResponseMessage(String status,Object info) {
        this.status = status;
        if (RESPONSE_SUCCESS.equals(status)){
            this.data = info;
            this.errorinfo = "";
        }
        if (RESPONSE_FAIL.equals(status)){
            this.errorinfo = info.toString();
            this.data = "";
        }
    }

    public static ResponseMessage GetSuccessMessage(Object data){
         return new ResponseMessage(RESPONSE_SUCCESS, data);
    }
    public static ResponseMessage GetErrorMessage(Object data){
        return new ResponseMessage(RESPONSE_FAIL, data);
    }

    /**
     * 数据获取成功
     */
    public static final String RESPONSE_SUCCESS = "success";
    /**
     * 数据获取失败(非异常失败)
     */
    public static final String RESPONSE_FAIL    = "error";

    /**
     * 数据
     */
    private Object data;

    /**
     *状态信息
     */
    private String status;

    /**
     * 异常数据
     */
    private String errorinfo;
}
