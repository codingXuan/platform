package com.commerce.platform.handler;

import com.commerce.platform.util.TraversalMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by java_ztx on 2018/1/4.
 * 参数长度校验
 */
@Component
public class ParamCheckLengthHandler extends ReqParamHandler{
    @Autowired
    TraversalMapUtil tmu;

    @Override
    public void doHandler(Map<String, Object> paraMap) {
        //首先进行长度校验，校验完后抛给格式校验处理类
        tmu.checkLength(paraMap);
        if(getNextHandler() != null){
            getNextHandler().doHandler(paraMap);
        }
    }
}
