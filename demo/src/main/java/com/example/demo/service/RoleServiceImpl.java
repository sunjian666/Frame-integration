package com.example.demo.service;

import com.example.demo.domain.SysRole;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.mapper.PermissionDao;
import com.example.demo.mapper.RoleDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.util.ConstantUtil;
import com.example.demo.util.ResourceUtil;
import com.example.demo.util.RoleUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public PageInfo<SysRole> selectRoleDeeply(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> sysRoleList = roleDao.selectRoleDeeply(ConstantUtil.USE);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleList);
        return pageInfo;
    }

    @Override
    public SysRole selectRoleDeeplyOfOne(Integer id) {
        return roleDao.selectRoleDeeplyOfOne(id, ConstantUtil.USE);
    }

    @Override
    public void insertRole(SysRole sysRole, HttpServletRequest request) {
        sysRole.setName("ROLE_" + sysRole.getName());
        sysRole.setStatus(ConstantUtil.USE);

        //String name = userDao.findByUserName(RoleUtil.getUserName(), ConstantUtil.USE).getName();
        String name = jwtUtil.getNameFromToken(request);

        sysRole.setCreatedBy(name);
        sysRole.setLastModifiedBy(name);

        roleDao.insertRole(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole, HttpServletRequest request) {

        //String name = userDao.findByUserName(RoleUtil.getUserName(), ConstantUtil.USE).getName();
        String name = jwtUtil.getNameFromToken(request);
        sysRole.setLastModifiedBy(name);

        roleDao.updateRole(sysRole.getId(),sysRole.getName(),sysRole.getDescription(),sysRole.getLastModifiedBy(),sysRole.getStatus());
    }

    @Override
    public void deleteRole(Integer id) {
        roleDao.deleteRole(id,ConstantUtil.DELETED);

        roleDao.deleteOldRelationshipOfRole(id);
        roleDao.deleteOldRelationship(id);

       this.updateCodeArr(id);

    }

    @Override
    public Integer roleCount(String name) {
        return roleDao.roleCount(name);
    }

    @Override
    public void updateRelationship(Integer roleId, List<Integer> permissionIdList) {
        roleDao.deleteOldRelationship(roleId);

        if (permissionIdList != null){
            roleDao.saveNewRelationship(roleId, permissionIdList);
        }

        this.updateCodeArr(roleId);
    }

    private void updateCodeArr(Integer roleId){
        List<Integer> userIdList = roleDao.selectUserIdOfRoleId(roleId);
        //②查询系统中最大的权限位
        Integer maxPos = permissionDao.selectSystemMaxPos(ConstantUtil.USE);
        for (Integer userId : userIdList){
            //3.计算权限码数组的值
            //①深度加载Set<Role>
            List<SysRole> roles = userDao.selectUserOne(userId, ConstantUtil.USE).getRoles();

            //③计算数值
            String codeArr = ResourceUtil.calculateCodeArr(roles, maxPos);

            //④执行更新
            userDao.updateCodeArr(userId, codeArr, ConstantUtil.USE);
        }
    }
}
