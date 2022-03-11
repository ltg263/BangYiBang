package com.jxxx.byb.bean;


import com.jxxx.byb.base.BaseResponse;

import retrofit2.http.Query;

/**
 * Create by Sxl on 2020/11/20.
 */
public class LoginRequest extends BaseResponse {
    private String mobile;//	手机号
    private String event;//	事件名称
    private String captcha;//	验证码
    private String password;//密码
    private String account;//	账号
    private String newpassword;//	新密码

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
