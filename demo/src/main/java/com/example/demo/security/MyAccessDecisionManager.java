package com.example.demo.security;

import com.example.demo.domain.Permission;
import com.example.demo.mapper.PermissionDao;
import com.example.demo.mapper.UserDao;
import com.example.demo.service.PermissionService;
import com.example.demo.util.ResourceUtil;
import com.example.demo.util.RoleUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//接口决策器
@Service
public class MyAccessDecisionManager implements AccessDecisionManager{

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionService permissionService;


    // decide 方法是判定是否拥有权限的决策方法
    //authentication 是释CustomUserService中循环添加到 GrantedAuthority 对象中的权限信息集合.
    //object 包含客户端发起的请求的requset信息，可转换为
    // HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
    //configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，
    // 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。
    // 如果不在权限表中则放行。
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        //HttpServletRequest request = ((FilterInvocation)object).getHttpRequest();
        java.util.Iterator it = configAttributes.iterator();
        while(it.hasNext()) {
            MyConfigAttribute ip = (MyConfigAttribute) it.next();//next()返回的是object类型，需要转化，这个强转是正常写法
            HttpServletRequest request = ip.getHttpServletRequest();

            String url, method;
            AntPathRequestMatcher matcher;
            if ("anonymousUser".equals(authentication.getPrincipal())
                    || "ROLE_SUPER".equals(RoleUtil.getRole())
                    || matchers("/images/**", request)
                    || matchers("/js/**", request)
                    || matchers("/css/**", request)
                    || matchers("/fonts/**", request)
                    || matchers("/", request)
                    || matchers("/index.html", request)
                    || matchers("/favicon.ico", request)
                    || matchers("/refresh", request)) {
                return;
            } else {

                    /*for (GrantedAuthority ga : authentication.getAuthorities()) {   //authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                        if (ga instanceof MyGrantedAuthority) {
                            MyGrantedAuthority urlGrantedAuthority = (MyGrantedAuthority) ga;
                            url = urlGrantedAuthority.getPermissionUrl();
                            method = urlGrantedAuthority.getMethod();
                            matcher = new AntPathRequestMatcher(url);
                            if (matcher.matches(request)) {
                                //当权限表权限的method为ALL时表示拥有此路径的所有请求方式权利。
                                if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                                    return;
                                }
                            }
                        } else if (ga.getAuthority().equals("ROLE_ANONYMOUS")) { //未登录只允许访问 login 页面
                            matcher = new AntPathRequestMatcher("/login");
                            if (matcher.matches(request)) {
                                return;
                            }
                        }
                    }*/

                url = request.getServletPath();
                method = request.getMethod();

                Integer resCode;
                Integer resPos;

                Boolean resAlreadyExists = permissionService.checkResExists(url, method);

                if (resAlreadyExists){
                    Permission permission = permissionService.selectPermissionOfCodePos(url, method);
                    resCode = permission.getResCode();
                    resPos = permission.getResPos();

                    String userName = RoleUtil.getUserName();
                    String codeArr = userDao.selectCodeArr(userName);

                    if(StringUtils.isEmpty(codeArr)){
                        throw new AccessDeniedException("no right");
                    }

                    boolean b = ResourceUtil.checkAuthority(codeArr, resCode, resPos);
                    if (b){
                        return;
                    }else{
                        throw new AccessDeniedException("no right");
                    }
                }else{
                    Permission permissionCode = permissionService.createPermissionCode(url, method);
                    permissionService.insertPermission(permissionCode);

                    throw new AccessDeniedException("no right");
                }

                /*for (GrantedAuthority ga : authentication.getAuthorities()) {
                    if (ga instanceof SimpleGrantedAuthority) {
                        SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority)ga;
                        String authority = simpleGrantedAuthority.getAuthority();
                        List<Auths> byAuth = permissionDao.findByAuth(authority);
                        for (int i = 0;i < byAuth.size();i++){
                            url = byAuth.get(i).getUrl();
                            method = byAuth.get(i).getMethod();
                            matcher = new AntPathRequestMatcher(url);
                            if (matcher.matches(request)) {
                                //当权限表权限的method为ALL时表示拥有此路径的所有请求方式权利。
                                if (method.equals(request.getMethod()) || "ALL".equals(method)) {
                                    return;
                                }
                            }
                        }
                    }else if (ga.getAuthority().equals("ROLE_ANONYMOUS")) { //未登录只允许访问 login 页面
                        matcher = new AntPathRequestMatcher("/login");
                        if (matcher.matches(request)) {
                            return;
                        }
                    }
                }*/

                }
            }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }
}
