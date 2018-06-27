package com.example.demo.social.support;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

//@Component
public class DemoConnectionSignUp implements ConnectionSignUp {  //暂时定义以后会扩展
    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        return connection.getDisplayName();
    }
}
