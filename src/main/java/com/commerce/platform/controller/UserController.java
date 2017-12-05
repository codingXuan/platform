package com.commerce.platform.controller;

import com.commerce.platform.common.Constance;
import com.commerce.platform.common.Result;
import com.commerce.platform.domain.User;
import com.commerce.platform.service.UserService;
import com.commerce.platform.util.EncryptUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.lang.StringUtils;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2017/12/2 0002.
 * 用户管理控制层
 */
@RestController
public class UserController {

    @Autowired
    public UserService userService;

    /**
     * 用户注册
     * @param paraMap
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value="用户注册:/user/register",notes="邮箱做为用户名",httpMethod="POST",response=Result.class)
    @ApiResponse(code=200,message="注册成功")
    public Result register(HttpServletRequest request, @RequestBody @ApiParam(name="paraMap",value="注册请求参数:{\"email\":\"邮箱\","
            + "\"password\":\"密码\","
            + "\"method\":\"方法名\"}",required=true) Map<String, String> paraMap) {
        Result result = new Result();
        try {
            String email = paraMap.get("email");
            String password = EncryptUtils.decryption(paraMap.get("password"),EncryptUtils.desKey);
            if (StringUtils.isEmpty(email)) {
                result.setMsg("邮箱不能为空！");
                result.setCode(Constance.RESPONSE_PARAM_EMPTY);
                return result;
            }
            if (StringUtils.isEmpty(password)) {
                result.setMsg("密码不能为空！");
                result.setCode(Constance.RESPONSE_PARAM_EMPTY);
                return result;
            }
            //正则表达式的模式
            String rule_psd="(?![^a-zA-Z0-9]+$)(?![^a-zA-Z/D]+$)(?![^0-9/D]+$).{8,}$";
            Pattern p = Pattern.compile(rule_psd);
            //正则表达式的匹配器
            Matcher m = p.matcher(password);
            if(!m.matches()){
                result.setCode(Constance.RESPONSE_PARAM_ERROR);
                result.setMsg("密码必须为8位以上，包含字母、数字或特殊字符");
                return result;
            }
            String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
            //正则表达式的模式
            Pattern p1 = Pattern.compile(RULE_EMAIL);
            //正则表达式的匹配器
            Matcher m1 = p1.matcher(paraMap.get("email").toString());
            if(!m1.matches()){
                result.setMsg("邮箱格式不正确！");
                result.setCode(Constance.RESPONSE_PARAM_ERROR);
                return result;
            }

            List<User> listUser = userService.getUserByEmail(paraMap.get("email").toString());
            if (listUser != null && listUser.size() > 0) {
                if(listUser.get(0).getStatus().equals("1")){
                    result.setMsg("邮箱已被注册！");
                    result.setCode(Constance.RESPONSE_PARAM_ERROR);
                    return result;
                }
            }
            paraMap.put("password",password);
            result = userService.insertAdminUser(paraMap);
        } catch (Exception e) {
            result.setMsg("内部错误！");
            result.setCode(Constance.RESPONSE_ERROR);
            e.printStackTrace();
        }
        return result;
    }
}
