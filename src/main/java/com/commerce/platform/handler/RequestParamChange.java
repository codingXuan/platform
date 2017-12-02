package com.commerce.platform.handler;

import com.commerce.platform.factory.ParamsChangeFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 参数转换的实现类
 */
@Component
public class RequestParamChange implements ParamsChangeFactory{
    @Override
    public ParamsChangeHandler paramChange() {
        return new ParamsChangeHandler();
    }
}
