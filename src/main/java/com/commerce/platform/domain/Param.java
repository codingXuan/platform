package com.commerce.platform.domain;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
public class Param {

    private String srcParam;  //返回给前台的参数
    private String destParam; //源参数
    private String status;   //0为无默认值，非0即为默认值
    private String method;  //反射调用工厂的方法名称

    public String getSrcParam() {
        return srcParam;
    }

    public void setSrcParam(String srcParam) {
        this.srcParam = srcParam;
    }

    public String getDestParam() {
        return destParam;
    }

    public void setDestParam(String destParam) {
        this.destParam = destParam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
