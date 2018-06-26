package com.example.demo.service;

import com.example.demo.domain.SysUser;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import java.util.List;


public interface UserService {

    void insertUser(SysUser user, HttpServletRequest request);

    Boolean countUser(String username);

    void updateUser(SysUser user, HttpServletRequest request);

    SysUser selectUserOne(Integer id);

    PageInfo<SysUser> selectUserAll(Integer pageNum, Integer pageSize);

    void deleteUser(Integer id);

    void updateRelationship(Integer userId, List<Integer> roleIdList);

}
