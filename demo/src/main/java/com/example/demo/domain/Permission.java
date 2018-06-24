package com.example.demo.domain;

public class Permission extends AbstractAuditingEntity{

    private Integer id;
    //权限名称
    private String name;

    //权限描述
    private String description;

    //授权链接
    private String url;

    //权限码
    private Integer resCode;

    //权限位
    private Integer resPos;

    //请求方法
    private String method;

    private String status;

    private Integer functionId;

    public Permission() {
    }

    public Permission(Integer id, String name, String description, String url, Integer resCode, Integer resPos, String method, String status, Integer functionId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.resCode = resCode;
        this.resPos = resPos;
        this.method = method;
        this.status = status;
        this.functionId = functionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public Integer getResPos() {
        return resPos;
    }

    public void setResPos(Integer resPos) {
        this.resPos = resPos;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", resCode=" + resCode +
                ", resPos=" + resPos +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", functionId=" + functionId +
                '}';
    }
}
