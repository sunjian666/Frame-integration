package com.example.demo.service;

import com.example.demo.domain.SysRole;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.mapper.FunctionDao;
import com.example.demo.mapper.PermissionDao;
import com.example.demo.domain.Function;
import com.example.demo.domain.Permission;
import com.example.demo.dto.PermissionAndHistory;
import com.example.demo.mapper.RoleDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.util.ConstantUtil;
import com.example.demo.util.ResourceUtil;
import com.example.demo.util.RoleUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void insertPermission(Permission permission, HttpServletRequest request) {

        /*String userName = RoleUtil.getUserName();
        if (StringUtils.isNotEmpty(userName)){
            String name = userDao.findByUserName(RoleUtil.getUserName(), ConstantUtil.USE).getName();

            permission.setCreatedBy(name);
            permission.setLastModifiedBy(name);
        }*/

        String name = jwtUtil.getNameFromToken(request);

        permission.setCreatedBy(name);
        permission.setLastModifiedBy(name);

        permissionDao.insertPermission(permission);
    }

    @Override
    public void updatePermission(Permission permission, HttpServletRequest request) {

        //String name = userDao.findByUserName(RoleUtil.getUserName(), ConstantUtil.USE).getName();
        String name = jwtUtil.getNameFromToken(request);
        permission.setLastModifiedBy(name);
        permissionDao.updatePermission(permission.getId(), permission.getName(), permission.getUrl(), permission.getMethod(), permission.getLastModifiedBy(), permission.getStatus());
    }

    @Override
    public void deletePermission(Integer id) {
        permissionDao.deletePermission(id, ConstantUtil.DELETED);
        roleDao.deleteOldRelationshipOfPermission(id);

        List<Integer> userIdList = permissionDao.selectUserIdOfPermission(id, ConstantUtil.USE);
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

    @Override
    public Permission createPermissionCode(String url, String method) {

        Integer resCode;
        Integer resPos;

            //①计算系统允许的最大权限码数值
            Integer systemAllowedMaxCode = 1 << 30;

            //②声明两个变量，并指定初始值，作为最终使用的权限码和权限位
            resCode = 1;
            resPos = 0;

            //③查询系统中最大的权限位数值
            //这里需要注意的是maxPos不存在时会是null值，不能使用基本数据类型接收
            Integer maxPos = this.selectSystemMaxPos();

            //如果maxPos为null，那么对应权限码的值应该也是null
            //如果maxPos不为null，那么查询最大权限码时一定要在maxPos范围内查询
            Integer maxCode = (maxPos == null) ? null : this.selectSystemMaxCode(maxPos);

            //④检查maxCode是否达到了系统允许的最大值
            if (maxPos != null && maxCode != null) {

                //※注意：将所有数据类型都统一为包装类型，用equals()方法比较其数值是最保险的做法
                if (maxCode.equals(systemAllowedMaxCode)) {

                    //权限位+1
                    resPos = maxPos + 1;

                    //权限码重新从1开始
                    resCode = 1;
                } else {

                    //权限位仍然使用最大值
                    resPos = maxPos;

                    //权限码在原来基础上左移一位
                    resCode = maxCode << 1;

                }

            }

            Permission permission = new Permission(null, null, null, url, resCode, resPos, method, ConstantUtil.USE, null);
            return permission;

    }

    @Override
    public PageInfo<Permission> selectPermission(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Permission> permissions = permissionDao.selectPermission(ConstantUtil.USE);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);

        return pageInfo;
    }

    @Override
    public Permission selectPermissionOfOne(Integer permissionId) {
        return permissionDao.selectPermissionOfOne(permissionId, ConstantUtil.USE);
    }


    @Override
    public Boolean checkResExists(String url, String method) {

        Integer count = permissionDao.checkResExists(url, method, ConstantUtil.USE);

        Boolean flag;

        if(count > 0 && count != null){
            flag = true;
        }else{
            flag = false;
        }


        return flag;
    }

    @Override
    public Permission selectPermissionOfCodePos(String url, String method) {
        return permissionDao.selectPermissionOfCodePos(url, method, ConstantUtil.USE);
    }

    @Override
    public Integer selectSystemMaxPos() {
        return permissionDao.selectSystemMaxPos(ConstantUtil.USE);
    }

    @Override
    public Integer selectSystemMaxCode(Integer maxPos) {
        return permissionDao.selectSystemMaxCode(maxPos, ConstantUtil.USE);
    }
}
