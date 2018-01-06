package com.commerce.platform.service.impl;

import com.commerce.platform.common.Constance;
import com.commerce.platform.common.Result;
import com.commerce.platform.factory.ParamsChange;
import com.commerce.platform.factory.ParamsChangeFactory;
import com.commerce.platform.handler.TestHandler;
import com.commerce.platform.mapper.PlatformMapper;
import com.commerce.platform.service.ParamChangeService;
import com.commerce.platform.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 控制台实现类
 */
@Service
public class PlatformServiceImpl implements PlatformService{

    @Autowired
    private PlatformMapper pfm;

    @Qualifier("testHandler")
    @Autowired
    public ParamsChange pc;

    @Override
    public Result changeParam(Map<String, Object> paraMap) throws Exception {
        Result result = new Result();
        pc.paramChange(paraMap);
        result.setCode(Constance.RESPONSE_SUCCESS);
        result.setData(paraMap);
        result.setMsg("参数转换成功");
        return result;
    }
}
