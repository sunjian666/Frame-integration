package com.example.demo.service;

import com.example.demo.dao.PermissionDao;
import com.example.demo.domain.Function;
import com.example.demo.domain.Permission;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleAndPermissionServiceImpl implements RoleAndPermissionService{

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public void insertPermissionFunction(Function function) {
        permissionDao.insertPermissionFunction(function);
    }

    @Override
    public void updatePermissionFunction(Function function) {
        permissionDao.updatePermissionFunction(function);
    }

    @Override
    public void deletePermissionFunction(Integer id) {
        permissionDao.deletePermissionFunction(id);
    }

    @Override
    public PageInfo<Function> selectPermissionFunction(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Function> functions = permissionDao.selectPermissionFunction(id);
        PageInfo<Function> pageInfo = new PageInfo<>(functions);
        return pageInfo;
    }

    @Override
    public void insertPermission(Permission permission) {
        permissionDao.insertPermission(permission);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
    }

    @Override
    public void deletePermission(Integer id) {
        permissionDao.deletePermission(id);
    }
}
