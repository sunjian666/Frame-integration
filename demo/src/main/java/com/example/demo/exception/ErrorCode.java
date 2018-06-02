package com.example.demo.exception;

public enum ErrorCode {

    TOKEN_INVALID("8081", "无效的token"),
    USERNAME_AND_PASSWORD_ERROR("8082", "用户名或密码错误，请重新输入！"),
    NO_ACCESS("8083", "无权限访问");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public String getCode(){
        return this.code;
    }
}
