package com.commerce.platform.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
@Transactional
public interface ParamChangeService {
    /**
     * 根据第三方id查询参数模板
     * @param paraMap
     * @return
     * @throws Exception
     */
     List<Map<String,Object>> paramChange(Map<String, Object> paraMap) throws Exception;
}
