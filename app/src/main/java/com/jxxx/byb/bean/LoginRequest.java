package com.jxxx.byb.bean;


import com.jxxx.byb.base.BaseResponse;

/**
 * Create by Sxl on 2020/11/20.
 */
public class LoginRequest extends BaseResponse {
//    {
//        "captchaKey": "1234-1234-1234-1234",
//            "clientType": "APP",
//            "password": "123456",
//            "phone": "18058525327",
//            "pictureVerificationCode": "1234"

//    smsVerificationCode
//    }
    private String mobile;//	手机号
    private String event;//	事件名称
    private String captcha;//	验证码
    private String password;//密码
    private String account;//	账号
    private String newpassword;//	新密码

    private String captchaKey;
    private String clientType = "APP";
    private String pictureVerificationCode;
    private String smsVerificationCode;
    private String scene;//场景枚举 1注册 2登录 3找回密码

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

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getScene() {
        return scene;
    }

    public void setSmsVerificationCode(String smsVerificationCode) {
        this.smsVerificationCode = smsVerificationCode;
    }

    public String getSmsVerificationCode() {
        return smsVerificationCode;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getClientType() {
        return clientType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPictureVerificationCode() {
        return pictureVerificationCode;
    }

    public void setPictureVerificationCode(String pictureVerificationCode) {
        this.pictureVerificationCode = pictureVerificationCode;
    }
}
