package com.commerce.platform.service;

import com.commerce.platform.common.Result;
import com.commerce.platform.domain.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
@Transactional
public interface UserService {
    List<User> getUserByEmail(String email);

    Result insertAdminUser(Map<String, String> paraMap) throws Exception;
}
