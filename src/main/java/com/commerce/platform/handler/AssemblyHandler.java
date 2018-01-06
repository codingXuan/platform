package com.commerce.platform.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by java_ztx on 2018/1/4.
 * 责任链组装类
 */
@Component
public class AssemblyHandler {
    @Autowired
    ParamCheckLengthHandler pclh;
    @Autowired
    ParamCheckFormatHandler pcfh;


    /**
     * 入参模板转换
     */
    public void assemblyReqHandler(Map<String,Object> paraMap){
        pclh.setNextHandler(pcfh);
        pclh.doHandler(paraMap);
    }

}
