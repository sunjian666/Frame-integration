package com.example.demo.controller;

import com.example.demo.domain.SysUser;
import com.example.demo.social.support.SocialController;
import com.example.demo.social.support.SocialUserInfo;
import com.example.demo.util.AppSingUpUtils;
import com.example.demo.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppSecurityController extends SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSingUpUtils appSingUpUtils;

    /**
     * 需要注册时跳到这里，返回401和用户信息给前端
     * @param request
     * @return
     */
    @GetMapping(ConstantUtil.DEFAULT_SOCIAL_USER_INFO_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        appSingUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
        return buildSocialUserInfo(connection);
    }


    @PostMapping("/regist")
    public void regist(SysUser user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = user.getUsername();
        //providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
		appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
    }
}
