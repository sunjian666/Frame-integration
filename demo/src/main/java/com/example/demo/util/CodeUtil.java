package com.example.demo.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
    /**
     * 将获取到的前端参数转为string类型
     * @param request
     * @param key
     * @return
     */
    public static String getString(HttpServletRequest request, String key){
        try{
            String result = request.getParameter(key);
            if(result != null){
                result = result.trim();
            }
            if("".equals(result)){
                result = null;
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 验证码校验
     * @param
     * @return
     */
    public static boolean checkVerifyCode(String code, HttpServletRequest request){
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String v1 = code.toUpperCase();
        String v2 = verifyCodeExpected.toUpperCase();
        //获取用户输入的验证码
        //String verifyCodeActual = CodeUtil.getString(request, "verifyCodeActual");
        if (v1 == null || !v1.equals(v2)){
            return false;
        }
        return true;
    }
}
