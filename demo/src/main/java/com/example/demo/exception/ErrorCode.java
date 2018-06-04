package com.example.demo.exception;

public enum ErrorCode {

    TOKEN_INVALID("8081", "无效的token"),
    USERNAME_AND_PASSWORD_ERROR("8082", "用户名或密码错误，请重新输入！"),
    NO_ACCESS("8083", "无权限访问"),
    FUNCTION_INSERT_ERROR("8084", "资源功能插入错误"),
    FUNCTION_UPDATE_ERROR("8085", "资源功能更新错误"),
    FUNCTION_DELETE_ERROR("8086", "资源功能删除错误"),
    FUNCTION_SELECT_ERROR("8087", "资源功能查询错误"),
    PERMISSION_INSERT_ERROR("8088", "资源插入错误"),
    PERMISSION_UPDATE_ERROR("8089", "资源更新错误"),
    PERMISSION_DELETE_ERROR("8090", "资源删除错误"),
    PERMISSION_HISTORY_ERROR("8091", "历史资源查询错误");

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
