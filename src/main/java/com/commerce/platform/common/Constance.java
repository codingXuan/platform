package com.commerce.platform.common;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 该实体类为封装常量的实体类。
 * 前后台交互返回的状态码
 */
public class Constance {

    //操作成功 code为1000200
    public static String PARAM_SUCCESS="RESPONSE_SUCCESS";
    public static Integer RESPONSE_SUCCESS=1000200;
    //参数为空 code为1000501
    public static String PARAM_EMPTY="RESPONSE_PARAM_EMPTY";
    public static Integer RESPONSE_PARAM_EMPTY=1000501;
    //参数出错 code为1000502
    public static String PARAM_ERROR="RESPONSE_PARAM_ERROR";
    public static Integer RESPONSE_PARAM_ERROR=1000502;
    //参数超长 code为1000503
    public static String LENGTH_ERROR="RESPONSE_PARAM_LENGTH";
    public static Integer RESPONSE_PARAM_LENGTH=1000503;
    //未知错误 code为1000500
    public static Integer RESPONSE_ERROR=1000500;
    //错误代码的存储key
    public static String ERROR_CODE="RESPONSE_ERROR";







}
