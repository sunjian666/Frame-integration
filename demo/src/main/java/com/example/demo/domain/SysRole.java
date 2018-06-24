package com.example.demo.domain;

import java.util.List;

public class SysRole extends AbstractAuditingEntity{

    private Integer id;
    private String name;
    private String description;
    private String status;
    private List<Permission> permissionList;

    public SysRole() {
    }

    public SysRole(Integer id, String name, String description, String status, List<Permission> permissionList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.permissionList = permissionList;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", permissionList=" + permissionList +
                '}';
    }
}
