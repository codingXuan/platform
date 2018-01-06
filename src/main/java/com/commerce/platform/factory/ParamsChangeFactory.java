package com.commerce.platform.factory;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 提供工厂的接口
 */
@Component
public interface ParamsChangeFactory {
     ParamsChange createFactory();
}
