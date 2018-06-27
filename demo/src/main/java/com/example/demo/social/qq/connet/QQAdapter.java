package com.example.demo.social.qq.connet;

import com.example.demo.social.qq.api.QQ;
import com.example.demo.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ qq) {  //判断QQ服务是否还在
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues values) {

        QQUserInfo userInfo = qq.getUserInfo();

        values.setDisplayName(userInfo.getNickname());  //设置用户昵称
        values.setImageUrl(userInfo.getFigureurl_qq_1());  //设置用户头像
        values.setProfileUrl(null);  //设置用户个人主页
        values.setProviderUserId(userInfo.getOpenId());  //设置用户的OpenId

    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
