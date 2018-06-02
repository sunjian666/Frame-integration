package com.example.demo.security;

import org.springframework.security.access.ConfigAttribute;

import javax.servlet.http.HttpServletRequest;

public class MyConfigAttribute implements ConfigAttribute{

    private final HttpServletRequest httpServletRequest;

    public MyConfigAttribute(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    @Override
    public String getAttribute() {
        return null;
    }
}
