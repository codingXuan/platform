package com.commerce.platform.mapper;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */

@Mapper
public interface PlatformMapper {
     List<Map<String,Object>> getParam(Integer companyId);
}
