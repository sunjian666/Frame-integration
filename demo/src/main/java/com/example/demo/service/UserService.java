package com.example.demo.service;

import com.example.demo.domain.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface UserService {

    void insertUser(SysUser user);

    Boolean countUser(String username);

    void updateUser(SysUser user);

    SysUser selectUserOne(Integer id);

    PageInfo<SysUser> selectUserAll(Integer pageNum, Integer pageSize);

    void deleteUser(Integer id);

    void updateRelationship(Integer userId, List<Integer> roleIdList);

}
