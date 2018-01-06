package com.commerce.platform.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by java_ztx on 2017/12/14.
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger log = Logger.getLogger(WebLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * controller包和子包里面的所有公用方法
     */
    @Pointcut("execution(public * com.commerce.platform.controller..*.*(..))")
    public void weblog() {
    }

    @Before("weblog()")
    public void doBefore(JoinPoint joinpoint) throws Throwable {

        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer sb = new StringBuffer();
        // 记录下请求内容
        sb.append(" URL : [").append(request.getMethod()).append("]").append(request.getRequestURL().toString())
                .append(". \n 请求IP : ").append(request.getRemoteAddr()).append(". \n 方法 : ")
                .append(joinpoint.getTarget().getClass().getName()).append(".")
                .append(joinpoint.getSignature().getName()).append(". \n 参数列表 : ")
                .append(Arrays.toString(joinpoint.getArgs()));

        log.debug(sb.toString());
    }

    @AfterReturning(returning = "ret", pointcut = "weblog()")
    public void doAfterReturning(Object ret) throws Throwable {
        String str = (ret == null ? "null":ret.toString());
        if(str != null && str.length() > 50){
            str = str.substring(0, 46) +"...";
        }
        // 处理完请求，返回内容,对内容截取50个字符显示
        log.debug("返回值 : " + str + "\n 耗时：" + (System.currentTimeMillis() - startTime.get()) + "ms.");
    }

    @AfterThrowing(pointcut = "weblog()", throwing = "e")
    public void afterThrowing(JoinPoint joinpoint, Throwable e) {
        log.error("异常代码:" + e.getClass().getName());
        log.error("\n 异常信息:" + e.getMessage());
        log.error("\n 异常方法:" + joinpoint.getTarget().getClass().getName() + "." + joinpoint.getSignature().getName());
    }

}
