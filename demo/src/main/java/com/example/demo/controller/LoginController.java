package com.example.demo.controller;


import com.example.demo.jwt.JwtAuthenticationRequest;
import com.example.demo.jwt.JwtAuthenticationResponse;
import com.example.demo.service.AuthService;
import com.example.demo.exception.ErrorCode;
import com.example.demo.util.CodeUtil;
import com.example.demo.util.JsonResonse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResonse login(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletRequest request) {

        JsonResonse resonse = new JsonResonse();

        String code = authenticationRequest.getCode();

        if(StringUtils.isEmpty(code)){
            resonse.setCode(ErrorCode.VERIFY_CODE_NULL_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.VERIFY_CODE_NULL_ERROR.getMessage());
            return resonse;
        }

        if (!CodeUtil.checkVerifyCode(code, request)) {
            resonse.setCode(ErrorCode.VERIFY_CODE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.VERIFY_CODE_ERROR.getMessage());
            return resonse;
        }

        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        resonse.setData(new JwtAuthenticationResponse(token));

        return resonse;
    }

    @GetMapping(value = "/refresh")
    @ResponseBody
    public JsonResonse refresh(HttpServletRequest request){
        JsonResonse resonse = new JsonResonse();

        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);

        if (refreshedToken == null){
            resonse.setCode(ErrorCode.USERNAME_AND_PASSWORD_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.USERNAME_AND_PASSWORD_ERROR.getMessage());
            return resonse;
        }else{
            resonse.setData(new JwtAuthenticationResponse(refreshedToken));
            return resonse;
        }
    }
}
