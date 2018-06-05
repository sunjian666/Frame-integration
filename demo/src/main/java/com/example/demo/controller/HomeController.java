package com.example.demo.controller;

import com.example.demo.domain.Msg;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    /*@RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }*/

    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }

    /*@RequestMapping("/login")
    public String login(){
        return "login";
    }*/

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@AuthenticationPrincipal UserDetails user){ //可通过此方法获取当前用户的账号密码权限信息
        return user;
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String save(){
        return "hello save";
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public String update(){
        return "hello update";
    }

}
