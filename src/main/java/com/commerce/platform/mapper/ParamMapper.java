package com.commerce.platform.mapper;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by java_ztx on 2018/1/3.
 */

@Mapper
public interface ParamMapper {
    List<Map<String,Object>> paramChange(@Param("paraMap") Map<String, Object> paraMap);
}
