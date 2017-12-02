package com.commerce.platform.common;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 该实体类为返回给前台的result对象。
 * 统一要求:msg 状态信息 code 状态码 data 参数
 */
public class Result {

    private Object data;
    private Integer code;
    private String msg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
