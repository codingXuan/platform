package com.commerce.platform.util;

import com.commerce.platform.common.Constance;
import com.commerce.platform.service.ParamChangeService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对参数格式、默认值、自动补齐等操作
 * 进行封装。
 * 超过200长度的删除掉该key
 * 超长返回错误码1000503.
 * 格式错误返回1000502
 * 无错误返回1000200.
 * Created by java_ztx on 2018/1/2.
 */
@Component
public class TraversalMapUtil {

    @Autowired
    ParamChangeService pcs;

    /**
     * 对map里的所有值进行长度校验
     * @param paraMap
     * @return paraMap
     */
    public void checkLength(Map<String,Object> paraMap){
        //定义一个map存储格式超长的指令
        Map<String,Object> lengthMap=new HashedMap<>();
        //定义一个集合用来存储超长指令
        List<Map<String,Object>> lengthMaps=new ArrayList<>();
        for(String key:paraMap.keySet()){
            if(paraMap.get(key).toString().length()>200){
                paraMap.remove(key);
                lengthMap.put(key,paraMap.get(key));
                lengthMaps.add(lengthMap);
            }
        }
        //如果集合中有元素，则说明有超长指令
        if(lengthMaps.size()>0){
            paraMap.put(Constance.LENGTH_ERROR,Constance.RESPONSE_PARAM_LENGTH);
            paraMap.put(Constance.ERROR_CODE,lengthMaps);
        }else{
            paraMap.put(Constance.PARAM_SUCCESS,Constance.RESPONSE_SUCCESS);
        }
    }

    /**
     * 通用格式校验
     * 校验成功放回1000200
     * 校验失败返回
     * @param  paraMap
     * @return paraMap
     */
    public void checkParam(Map<String,Object> paraMap){
        //如果有IP则进行格式校验没有则放行
        if(paraMap.containsKey("ip")){
            String ip=paraMap.get("ip").toString();
            String ipRegx = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    +"(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            Pattern p=Pattern.compile(ipRegx);
            Matcher m=p.matcher(ip);
            if(!m.matches()){
                paraMap.put(Constance.PARAM_ERROR,Constance.RESPONSE_PARAM_ERROR);
                paraMap.put(Constance.ERROR_CODE,ip);
                return;
            }
        }
        //如果有邮箱则进行邮箱格式校验没有则放行
        if(paraMap.containsKey("email")){
            String email=paraMap.get("email").toString();
            String emailRegx="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
            Pattern p=Pattern.compile(emailRegx);
            Matcher m=p.matcher(email);
            if(!m.matches()){
                paraMap.put(Constance.PARAM_ERROR,Constance.RESPONSE_PARAM_ERROR);
                paraMap.put(Constance.ERROR_CODE,email);
                return;
            }
        }
        //验证电话号码 后可接分机号 区号3位或者4位 电话7位或者8位后 后面可加3位或者4位分机号
        if(paraMap.containsKey("telephoneNO")){
            String telephoneNO=paraMap.get("telephoneNO").toString();
            String telephoneNORegx="^\\\\d{3,4}\\\\d{7,8}(\\\\d{3,4})?$";
            Pattern p=Pattern.compile(telephoneNORegx);
            Matcher m=p.matcher(telephoneNO);
            if(!m.matches()){
                paraMap.put(Constance.PARAM_ERROR,Constance.RESPONSE_PARAM_ERROR);
                paraMap.put(Constance.ERROR_CODE,telephoneNO);
                return;
            }
        }
        //验证手机号码 电话号码字符串 130到139 和 150，152 ，157，158，159 ，186，189，187
        if(paraMap.containsKey("mobileNO")){
            String mobileNO=paraMap.get("mobileNO").toString();
            String mobileNORegx="^(1[358][13567890])(\\\\d{8})$";
            Pattern p=Pattern.compile(mobileNORegx);
            Matcher m=p.matcher(mobileNO);
            if(!m.matches()){
                paraMap.put(Constance.PARAM_ERROR,Constance.RESPONSE_PARAM_ERROR);
                paraMap.put(Constance.ERROR_CODE,mobileNO);
                return;
            }
        }
        paraMap.put(Constance.PARAM_SUCCESS,Constance.RESPONSE_SUCCESS);
    }

}
