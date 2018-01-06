package com.commerce.platform.factory;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 提供业务处理的接口
 */
@Component
public interface ParamsChange {
     void paramChange(Map<String, Object> paraMap);
}
