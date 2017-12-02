package com.commerce.platform.handler;

import com.commerce.platform.factory.ParamsChange;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 参数转换处理类
 */
@Component
public class ParamsChangeHandler implements ParamsChange {


    @Override
    public Map<String, Object> paramChange(Map<String,Object> paraMap) {

        return paraMap;
    }
}
