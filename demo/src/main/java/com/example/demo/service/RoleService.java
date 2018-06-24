package com.example.demo.service;

import com.example.demo.domain.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    PageInfo<SysRole> selectRoleDeeply(Integer pageNum, Integer pageSize);
    SysRole selectRoleDeeplyOfOne(Integer id);

    void insertRole(SysRole sysRole);
    void updateRole(SysRole sysRole);
    void deleteRole(Integer id);

    Integer roleCount(String name);

    void updateRelationship(Integer roleId, List<Integer> permissionIdList);

}
