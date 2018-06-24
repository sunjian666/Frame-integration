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
    PERMISSION_SELECT_ERROR("8091", "资源查询错误"),
    VERIFY_CODE_ERROR("8092", "验证码输入错误"),
    VERIFY_CODE_NULL_ERROR("8093", "验证码不能为空"),
    ROLE_INSERT_ERROR("8094", "权限插入错误"),
    ROLE_UPDATE_ERROR("8095", "权限更新错误"),
    ROLE_DELETE_ERROR("8096", "权限删除错误"),
    ROLE_SELECT_ERROR("8097", "权限查询错误"),
    USER_ALREADY_EXIST("8098", "用户已存在"),
    ROLE_ALREADY_EXIST("8099", "权限已存在"),
    PERMISSION_ALREADY_EXIST("8100", "资源已存在"),
    FUNCTION_ALREADY_EXIST("8101", "功能已存在"),
    USER_INSERT_ERROR("8102", "注册用户失败"),
    USER_UPDATE_ERROR("8103","更新用户失败"),
    USER_DELETE_ERROR("8104","删除用户失败"),
    USER_SELECT_ERROR("8104","查询用户失败"),
    MODIFITY_USER_ROLE_ERROR("8105","修改用户权限错误");

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
