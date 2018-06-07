package com.example.demo.jwt;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.exception.CheckVerifyCodeException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.util.JsonResonse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject header = new JSONObject();
        if (authException instanceof BadCredentialsException){/**身份认证未通过*/
            header.put("code", ErrorCode.USERNAME_AND_PASSWORD_ERROR.getCode());
            header.put("success", "false");
            header.put("message", ErrorCode.USERNAME_AND_PASSWORD_ERROR.getMessage());
        } else{
            header.put("code", ErrorCode.TOKEN_INVALID.getCode());
            header.put("success", "false");
            header.put("message", ErrorCode.TOKEN_INVALID.getMessage());
        }
        response.getWriter().write(JSONObject.toJSONString(header));
    }
}
