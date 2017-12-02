package com.commerce.platform.controller;

import com.commerce.platform.LogApplication;
import com.commerce.platform.common.Constance;
import com.commerce.platform.common.Result;
import com.commerce.platform.service.PlatformService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 云平台管理控制层
 */
@RestController
@RequestMapping("platform")
public class PlatformController extends LogApplication{

    @Autowired
    public PlatformService pfs;

    /**
     * 控制台参数转换
     * 对接不同的第三方平台
     * 实行参数统一转换
     * @param request
     * @param paraMap
     * @return
     */
    @RequestMapping(value="change_param",method = RequestMethod.POST)
    public Result changeParam(HttpServletRequest request,@RequestBody Map<String, Object> paraMap){
        Result result = new Result();
        try {
            if(paraMap.isEmpty()){
                result.setCode(Constance.RESPONSE_PARAM_EMPTY);
                result.setMsg("参数传递为空!");
                return result;
            }

            /*String userId=request.getParameter("userId");*/
            String userId="1";
            paraMap.put("userId",userId);
            result=pfs.changeParam(paraMap);
        } catch (Exception e) {
            log.debug("changeParam",e);
            result.setCode(Constance.RESPONSE_ERROR);
            e.printStackTrace();
            result.setMsg("内部错误!");
        }
        return result;
    }

}
