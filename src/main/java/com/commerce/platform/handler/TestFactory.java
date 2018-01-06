package com.commerce.platform.handler;

import com.commerce.platform.factory.ParamsChangeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 参数转换的实现类
 */
@Component
public class TestFactory implements ParamsChangeFactory{

    @Autowired
    TestHandler testHandler;

    @Override
    public TestHandler createFactory() {
        return testHandler;
    }
}
