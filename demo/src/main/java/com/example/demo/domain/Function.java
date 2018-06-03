package com.example.demo.domain;

import java.util.List;

public class Function {

    private Integer id;
    private String name;
    private List<Permission> permissionList;

    public Function() {
    }

    public Function(Integer id, String name, List<Permission> permissionList) {
        this.id = id;
        this.name = name;
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

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissionList=" + permissionList +
                '}';
    }
}
