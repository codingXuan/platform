package com.commerce.platform.factory;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 参数转换的接口
 */
@Component
public interface ParamsChange {
    public Map<String,Object> paramChange(Map<String,Object> paraMap);
}
