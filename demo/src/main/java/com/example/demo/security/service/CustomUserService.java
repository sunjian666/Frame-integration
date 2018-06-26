package com.example.demo.security.service;

import com.example.demo.mapper.PermissionDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.domain.SysRole;
import com.example.demo.domain.SysUser;
import com.example.demo.util.ConstantUtil;
import com.example.demo.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomUserService implements UserDetailsService{  //自定义UserDetailsService 接口

    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {  //重写loadUserByUsername 方法获得 userdetails 类型用户

        SysUser user;
        if(ValidateUtil.checkCellphone(username) || ValidateUtil.checkTelephone(username)){
            user = userDao.findByMobile(username, ConstantUtil.USE);
        }else{
            user = userDao.findByUserName(username, ConstantUtil.USE);
        }

        if(user != null){
            /*List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions){
                if (permission != null && permission.getName() != null){
                    GrantedAuthority grantedAuthority = new MyGrantedAuthority(permission.getUrl(), permission.getMethod());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }*/
            List<SysRole> roles = user.getRoles();
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (int i = 0;i < roles.size();i++){
                String name = roles.get(i).getName();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(name);
                grantedAuthorities.add(grantedAuthority);
            }

            user.setGrantedAuthorities(grantedAuthorities);
            return user;
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }


}
