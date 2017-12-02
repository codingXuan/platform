package com.commerce.platform.mapper;


import com.commerce.platform.domain.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
@Mapper
@Repository
public interface PlatformMapper {
    public List<Map<String,Object>> getParam(Integer userId);
}
