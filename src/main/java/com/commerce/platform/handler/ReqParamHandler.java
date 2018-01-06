package com.commerce.platform.handler;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 责任链的核心处理类
 * Created by java_ztx on 2018/1/2.
 */
@Component
public abstract class ReqParamHandler {
    private ReqParamHandler nextHandler;

    public ReqParamHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(ReqParamHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void doHandler(Map<String,Object> paraMap);
}
