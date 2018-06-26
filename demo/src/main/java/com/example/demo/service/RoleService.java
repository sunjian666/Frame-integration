package com.example.demo.service;

import com.example.demo.domain.SysRole;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;

public interface RoleService {

    PageInfo<SysRole> selectRoleDeeply(Integer pageNum, Integer pageSize);
    SysRole selectRoleDeeplyOfOne(Integer id);

    void insertRole(SysRole sysRole, HttpServletRequest request);
    void updateRole(SysRole sysRole, HttpServletRequest request);
    void deleteRole(Integer id);

    Integer roleCount(String name);

    void updateRelationship(Integer roleId, List<Integer> permissionIdList);

}
