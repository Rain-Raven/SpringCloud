package com.zxa.common;

import lombok.Getter;

@Getter
public enum ApplicationConstant {
    OK(200,"OK"),
    PARAMETER_ERROR(50001,"参数错误"),
    EMAIL_IS_EXIST(50010,"邮箱已被注册"),
    MAILBOX_FORMAT_IS_INCORRECT(50011,"邮箱格式不正确"),
    CAPTCHA_IS_INCORRECT(50012,"验证码不正确"),
    EMAIL_IS_NOT_EXIST(50013,"邮箱不存在"),
    PASSWORD_IS_INCORRECT(50014,"密码不正确"),
    PLEASE_LOGIN(50015,"请登录后再操作"),
    SESSION_INFORMATION_INVALIDATION(50016,"会话信息已失效"),;
    private int code;
    private String message;

    ApplicationConstant(int code,String message){
        this.code=code;
        this.message=message;
    }
}
