package com.commerce.platform.factory;

import com.commerce.platform.handler.ParamsChangeHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 参数转换的工厂类
 */
@Component
public interface ParamsChangeFactory {
    public ParamsChangeHandler paramChange();
}
