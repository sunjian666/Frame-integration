package com.example.demo.service;

import com.example.demo.domain.Function;
import com.example.demo.domain.SysRole;
import com.example.demo.domain.SysUser;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.mapper.PermissionDao;
import com.example.demo.mapper.RoleDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void insertUser(SysUser user, HttpServletRequest request) {

        String password = user.getPassword();

        String encode = MD5Util.encode(password);
        user.setPassword(encode);

        user.setStatus(ConstantUtil.USE);

        //String name = userDao.findByUserName(RoleUtil.getUserName(), ConstantUtil.USE).getName();
        String name = jwtUtil.getNameFromToken(request);

        user.setCreatedBy(name);
        user.setLastModifiedBy(name);

        userDao.insertUser(user);

    }

    @Override
    public Boolean countUser(String username) {

        Integer countUser = userDao.countUser(username);

        return countUser > 0;
    }

    @Override
    public void updateUser(SysUser user, HttpServletRequest request) {

        //String name = userDao.findByUserName(RoleUtil.getUserName(),ConstantUtil.USE).getName();
        String name = jwtUtil.getNameFromToken(request);

        user.setLastModifiedBy(name);

        userDao.updateUser(user.getPassword(), user.getName(), user.getEmail(), user.getPhone(), user.getLastModifiedBy(), user.getId(), ConstantUtil.USE);

    }

    @Override
    public SysUser selectUserOne(Integer id) {
        return userDao.selectUserOne(id, ConstantUtil.USE);
    }

    @Override
    public PageInfo<SysUser> selectUserAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUsers = userDao.selectUserAll(ConstantUtil.USE);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        return pageInfo;
    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id,ConstantUtil.DELETED);
        userDao.deleteOldRelationship(id);
    }

    @Override
    @Transactional
    public void updateRelationship(Integer userId, List<Integer> roleIdList) {

        //1.删除旧的关联关系
        userDao.deleteOldRelationship(userId);

        //2.保存新的关联关系
        if (roleIdList != null){
            userDao.saveNewRelationshipOfUser(userId, roleIdList);
        }

        //3.计算权限码数组的值
        //①深度加载Set<Role>
        List<SysRole> roles = roleDao.selectRoleDeeplyOfId(roleIdList, ConstantUtil.USE);

        //②查询系统中最大的权限位
        Integer maxPos = permissionDao.selectSystemMaxPos(ConstantUtil.USE);

        //③计算数值
        String codeArr = ResourceUtil.calculateCodeArr(roles, maxPos);

        //④执行更新
        userDao.updateCodeArr(userId, codeArr, ConstantUtil.USE);

    }
}
