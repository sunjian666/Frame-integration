package com.example.demo.controller;

import com.example.demo.domain.Function;
import com.example.demo.domain.SysRole;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.RoleService;
import com.example.demo.util.JsonResonse;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public JsonResonse insertRole(@RequestBody SysRole role, HttpServletRequest request){
        JsonResonse resonse = new JsonResonse();
        String name = role.getName();

        try{
            if(roleService.roleCount(name) > 0){
                resonse.setCode(ErrorCode.ROLE_ALREADY_EXIST.getCode());
                resonse.setSuccess(false);
                resonse.setMessage(ErrorCode.ROLE_ALREADY_EXIST.getMessage());
                return resonse;
            }

            roleService.insertRole(role, request);
        }catch (Exception e){
            resonse.setCode(ErrorCode.ROLE_INSERT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.ROLE_INSERT_ERROR.getMessage());
        }
        return resonse;
    }

    @PutMapping("/role")
    public JsonResonse updateRole(@RequestBody SysRole role, HttpServletRequest request){
        JsonResonse resonse = new JsonResonse();
        try{
            roleService.updateRole(role, request);
        }catch (Exception e){
            resonse.setCode(ErrorCode.ROLE_UPDATE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.ROLE_UPDATE_ERROR.getMessage());
        }
        return resonse;
    }

    @DeleteMapping("/role")
    public JsonResonse deleteRole(@RequestParam("id") Integer id){
        JsonResonse resonse = new JsonResonse();
        try{
            roleService.deleteRole(id);
        }catch (Exception e){
            resonse.setCode(ErrorCode.ROLE_DELETE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.ROLE_DELETE_ERROR.getMessage());
        }
        return resonse;
    }

    @GetMapping("/roleOfDeeply")
    public JsonResonse selectRoleOfDeeply(@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        JsonResonse resonse = new JsonResonse();
        try{
            PageInfo<SysRole> sysRoles = roleService.selectRoleDeeply(pageNum, pageSize);
            resonse.setData(sysRoles);
        }catch (Exception e){
            resonse.setCode(ErrorCode.ROLE_SELECT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.ROLE_SELECT_ERROR.getMessage());
        }
        return resonse;
    }


    @GetMapping("/roleOfOne")
    public JsonResonse selectRoleOfOne(@RequestParam("id") Integer id){
        JsonResonse resonse = new JsonResonse();
        try{
            SysRole sysRole = roleService.selectRoleDeeplyOfOne(id);
            resonse.setData(sysRole);
        }catch (Exception e){
            resonse.setCode(ErrorCode.ROLE_SELECT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.ROLE_SELECT_ERROR.getMessage());
        }
        return resonse;
    }

}
