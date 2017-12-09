package com.commerce.platform.service.impl;

import com.commerce.platform.common.Constance;
import com.commerce.platform.common.Result;
import com.commerce.platform.domain.User;
import com.commerce.platform.mapper.UserMapper;
import com.commerce.platform.service.UserService;
import com.commerce.platform.util.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by Administrator on 2017/12/2 0002.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SendMailUtil sendMailUtil;
    @Value("${admin.email}")
    private String email;
    @Value("${site.safe.email}")
    private String ip;
    @Override
    public List<User> getUserByEmail(String email) {
        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("email", email);
        return userMapper.getUserByEmail(paraMap);
    }

    @Override
    public Result insertAdminUser(Map<String, String> paraMap) throws Exception{
        Result result = new Result();
        User user = new User();


        String fullpwd = Constance.KEY
                + paraMap.get("email")
                +paraMap.get("password");
        String md5pwd = DigestUtils.md5DigestAsHex(fullpwd.getBytes());


        user.setPassword(md5pwd);
        user.setEmail(paraMap.get("email"));
        user.setRole("1");
        user.setStatus("1");
        user.setMethod(paraMap.get("method"));

        userMapper.inserUser(user);
        //发送验证邮件
        String validateCode=DigestUtils.md5DigestAsHex(user.getEmail().getBytes());
        Session session=sendMailUtil.SimpleEmail();
        String content=paraMap.get("email").toString()+"您好，请点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>"
                + "<a href=\"http://"+ip+":8080/dist/#/active?action=activate&email="+paraMap.get("email").toString()+"&validateCode="+validateCode
                + "\">http://"+ip+":8080/dist/#/active?action=activate&email="+paraMap.get("email").toString()+"&validateCode="+validateCode
                + "</a>";
        MimeMessage message=sendMailUtil.createMimeMessage(session, email, paraMap.get("email").toString(), paraMap.get("email").toString(), content);
        sendMailUtil.sendMail(session, email, message);
        result.setCode(Constance.RESPONSE_SUCCESS);
        result.setMsg("注册成功,请先激活");
        return result;
    }

    @Override
    public Result login(Map<String, Object> paraMap, HttpServletRequest request) {
        Result result = new Result();
        String fullpwd = Constance.KEY
                + paraMap.get("email")
                +paraMap.get("password");
        String md5pwd = DigestUtils.md5DigestAsHex(fullpwd.getBytes());
        paraMap.put("password",md5pwd);
        List<User> user = userMapper.getUserByEmail(paraMap);
        if(user.size()>0){
            Map<String,Object> rparaMap=new HashMap<>();
            User loginUser=user.get(0);
            request.setAttribute("userId",loginUser.getUserId());
            String uuid= String.valueOf(UUID.randomUUID());
            rparaMap.put("uuid",uuid);
            result.setMsg("登录成功");
            result.setCode(Constance.RESPONSE_SUCCESS);
            result.setData(rparaMap);
        }else{
            result.setData("");
            result.setMsg("账号或者密码错误，登录失败!");
            result.setCode(Constance.RESPONSE_PARAM_ERROR);
        }
        return result;
    }
}
