package com.example.demo.controller;

import com.example.demo.domain.Msg;
import com.example.demo.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;

@RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }


    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }

@RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@AuthenticationPrincipal UserDetails user){ //可通过此方法获取当前用户的账号密码权限信息
        return user;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(HttpServletRequest request){

        String userAccount = jwtUtil.getUserAccountFromToken(request);
        List<String> authorities = jwtUtil.getAuthoritiesFromToken(request);
        String email = jwtUtil.getEmailFromToken(request);
        String name = jwtUtil.getNameFromToken(request);
        String phone = jwtUtil.getPhoneFromToken(request);

        Map<String, Object> map = new HashMap<>();
        map.put("userAccount", userAccount);
        map.put("authorities", authorities);
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        return map;
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public String update(){
        return "hello update";
    }

}
