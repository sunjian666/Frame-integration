package com.example.demo.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.util.List;

@ApiModel
public class JsonResonse {

    @ApiModelProperty(value = "状态码", name = "code", required=true)
    private String code;

    @ApiModelProperty(value = "返回状态", name = "success", required=true)
    private boolean success;

    @ApiModelProperty(value = "返回信息", name = "message", required=true)
    private String message;

    @ApiModelProperty(value = "返回参数信息", name = "fieldMessage", required=true)
    private List<String> fieldMessage;

    @ApiModelProperty(value = "返回内容", name = "data", required=true)
    private Object data;

    public JsonResonse() {
        code = HttpStatus.OK.toString();
        success = true;
        message = "操作成功";
    }

    public JsonResonse(List<String> fieldMessage) {
        code = HttpStatus.BAD_REQUEST.toString();
        success = false;
        message = "操作失败";
        this.fieldMessage = fieldMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(List<String> fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    @Override
    public String toString() {
        return "JsonResonse{" +
                "code='" + code + '\'' +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", fieldMessage=" + fieldMessage +
                ", data=" + data +
                '}';
    }
}
