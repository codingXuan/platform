package com.commerce.platform.service.impl;

import com.commerce.platform.mapper.ParamMapper;
import com.commerce.platform.service.ParamChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
@Service
public class ParamChangeServiceImpl implements ParamChangeService{
    @Autowired
    ParamMapper pm;

    @Override
    public List<Map<String, Object>> paramChange(Map<String, Object> paraMap) throws Exception {
        return pm.paramChange(paraMap);
    }
}
