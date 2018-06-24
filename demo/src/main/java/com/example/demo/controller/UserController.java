package com.example.demo.controller;

import com.example.demo.domain.SysUser;
import com.example.demo.dto.UpdateRelationshipOfUser;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonResonse;
import com.example.demo.util.MD5Util;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public JsonResonse insertUser(@RequestBody SysUser user){
        JsonResonse resonse = new JsonResonse();
        String username = user.getUsername();

        try{
            if(userService.countUser(username)){
                resonse.setCode(ErrorCode.USER_ALREADY_EXIST.getCode());
                resonse.setSuccess(false);
                resonse.setMessage(ErrorCode.USER_ALREADY_EXIST.getMessage());
                return resonse;
            }

            userService.insertUser(user);
        }catch (Exception e){
            resonse.setCode(ErrorCode.USER_INSERT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.USER_INSERT_ERROR.getMessage());
        }
        return resonse;
    }

    @PutMapping("/user")
    public JsonResonse updateUser(@RequestBody SysUser user){
        JsonResonse resonse = new JsonResonse();
        String password = user.getPassword();
        Integer id = user.getId();

        try{

            String originalPassword = userService.selectUserOne(id).getPassword();
            if(originalPassword.equals(password)){
                user.setPassword(MD5Util.encode(password));
            }

            userService.updateUser(user);
        }catch (Exception e){
            resonse.setCode(ErrorCode.USER_UPDATE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.USER_UPDATE_ERROR.getMessage());
        }
        return resonse;
    }

    @DeleteMapping("/user")
    public JsonResonse deleteUser(@RequestParam("id") Integer id){
        JsonResonse resonse = new JsonResonse();

        try{
            userService.deleteUser(id);
        }catch (Exception e){
            resonse.setCode(ErrorCode.USER_DELETE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.USER_DELETE_ERROR.getMessage());
        }
        return resonse;
    }

    @GetMapping("/userOfOne")
    public JsonResonse selectOfOne(@RequestParam("id") Integer id){
        JsonResonse resonse = new JsonResonse();

        try{
            SysUser sysUser = userService.selectUserOne(id);
            resonse.setData(sysUser);
        }catch (Exception e){
            resonse.setCode(ErrorCode.USER_SELECT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.USER_SELECT_ERROR.getMessage());
        }
        return resonse;
    }

    @GetMapping("/userOfAll")
    public JsonResonse selectOfAll(@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        JsonResonse resonse = new JsonResonse();

        try{
            PageInfo<SysUser> sysUserPageInfo = userService.selectUserAll(pageNum, pageSize);
            resonse.setData(sysUserPageInfo);
        }catch (Exception e){
            resonse.setCode(ErrorCode.USER_SELECT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.USER_SELECT_ERROR.getMessage());
        }
        return resonse;
    }

    @PutMapping("/userOfRelationship")
    public JsonResonse updateRelationship(@RequestBody UpdateRelationshipOfUser userCode){
        JsonResonse resonse = new JsonResonse();

        Integer userId = userCode.getUserId();
        List<Integer> roleIdList = userCode.getRoleIdList();

        try{
            userService.updateRelationship(userId, roleIdList);
        }catch (Exception e){
            resonse.setCode(ErrorCode.MODIFITY_USER_ROLE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.MODIFITY_USER_ROLE_ERROR.getMessage());
        }
        return resonse;
    }

}
