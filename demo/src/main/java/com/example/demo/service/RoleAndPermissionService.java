package com.example.demo.service;

import com.example.demo.domain.Function;
import com.example.demo.domain.Permission;
import com.example.demo.dto.PermissionAndHistory;
import com.github.pagehelper.PageInfo;


public interface RoleAndPermissionService {

    void insertPermissionFunction(Function function);
    void updatePermissionFunction(Function function);
    void deletePermissionFunction(Integer id);
    PageInfo<Function> selectPermissionFunction(Integer id, Integer pageNum, Integer pageSize);

    void insertPermission(Permission permission);
    void updatePermission(Permission permission);
    void deletePermission(Integer id);

    PermissionAndHistory selectPermissionHistory(String roleName);

}
