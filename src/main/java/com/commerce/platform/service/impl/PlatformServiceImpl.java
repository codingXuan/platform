package com.commerce.platform.service.impl;

import com.commerce.platform.common.Constance;
import com.commerce.platform.common.Result;
import com.commerce.platform.factory.ParamsChange;
import com.commerce.platform.factory.ParamsChangeFactory;
import com.commerce.platform.handler.ParamsChangeHandler;
import com.commerce.platform.handler.RequestParamChange;
import com.commerce.platform.mapper.PlatformMapper;
import com.commerce.platform.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    public ParamsChangeFactory pcf;
    @Autowired
    public ParamsChange pc;
    @Autowired
    public ParamsChangeHandler pch;

    @Override
    public Result changeParam(Map<String, Object> paraMap) throws Exception {
        Result result = new Result();
        List<Map<String,Object>> src=new ArrayList<>();
        List<Map<String,Object>> par = pfm.getParam(Integer.valueOf(paraMap.get("userId").toString()));
        paraMap.remove("userId");
        for(Map<String,Object> map:par){
            Map<String,Object> params=new HashMap<>();
            params.put("srcMap",paraMap);
            params.put("destMap",map);
            pcf=new RequestParamChange();
            pc=pcf.paramChange();
            Map<String,Object> srcMap=pch.paramChange(params);
            src.add(srcMap);
        }
        result.setCode(Constance.RESPONSE_SUCCESS);
        result.setData(src);
        result.setMsg("参数转换成功");
        return result;
    }
}
