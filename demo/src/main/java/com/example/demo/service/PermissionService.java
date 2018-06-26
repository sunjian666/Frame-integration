package com.example.demo.service;

import com.example.demo.domain.Permission;
import com.example.demo.dto.PermissionAndHistory;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface PermissionService {

    void insertPermission(Permission permission, HttpServletRequest request);
    void updatePermission(Permission permission, HttpServletRequest request);
    void deletePermission(Integer id);

    Permission createPermissionCode(String url, String method);

    PageInfo<Permission> selectPermission(Integer pageNum, Integer pageSize);
    Permission selectPermissionOfOne(Integer permissionId);

    Boolean checkResExists(String url, String method);
    Permission selectPermissionOfCodePos(String url, String method);
    Integer selectSystemMaxPos();
    Integer selectSystemMaxCode(Integer maxPos);

}
