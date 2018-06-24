package com.example.demo.dto;

import java.util.List;

public class UpdateRelationshipOfUser {

    private Integer userId;
    private List<Integer> roleIdList;

    public UpdateRelationshipOfUser() {
    }

    public UpdateRelationshipOfUser(Integer userId, List<Integer> roleIdList) {
        this.userId = userId;
        this.roleIdList = roleIdList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Override
    public String toString() {
        return "UpdateRelationshipOfUser{" +
                "userId=" + userId +
                ", roleIdList=" + roleIdList +
                '}';
    }
}
