package com.commerce.platform.service;

import com.commerce.platform.common.Result;
import org.springframework.transaction.annotation.Transactional;


import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
@Transactional
public interface PlatformService {
     Result changeParam(Map<String, Object> paraMap)throws Exception;
}
