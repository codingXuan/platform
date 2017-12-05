package com.commerce.platform.mapper;

import com.commerce.platform.common.Result;
import com.commerce.platform.domain.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
@Mapper
public interface UserMapper {
    List<User> getUserByEmail(Map<String, Object> email);

    Result inserUser(User user);
}
