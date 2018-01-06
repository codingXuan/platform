package com.commerce.platform.handler;

import com.commerce.platform.common.Constance;
import com.commerce.platform.util.TraversalMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by java_ztx on 2018/1/4.
 * 通用格式校验
 * 此处接收的参数为校验过长度后的
 * 故需要先判断长度校验是否通过
 */
@Component
public class ParamCheckFormatHandler extends ReqParamHandler{
    @Autowired
    TraversalMapUtil tmu;

    @Override
    public void doHandler(Map<String, Object> paraMap) {
        //判断长度校验是否成功
        if(!paraMap.containsKey(Constance.ERROR_CODE)&&!paraMap.containsKey(Constance.LENGTH_ERROR)){
            tmu.checkParam(paraMap);
            if(getNextHandler() != null){
                getNextHandler().doHandler(paraMap);
            }
        }
    }
}
