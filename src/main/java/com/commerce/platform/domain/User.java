package com.commerce.platform.domain;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
public class User {

    private Integer userId; //用户id
    private String email;  //邮箱
    private String password; //密码
    private String status; //状态 0为已激活 1为未激活
    private String role;  //权限 0为一般用户 1为管理用户
    private String method;//映射的方法

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }}
