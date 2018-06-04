package com.example.demo.controller;

import com.example.demo.domain.Function;
import com.example.demo.domain.Permission;
import com.example.demo.dto.PermissionAndHistory;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.RoleAndPermissionService;
import com.example.demo.util.JsonResonse;
import com.example.demo.util.RoleUtil;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleAndPermissionController {

    @Autowired
    private RoleAndPermissionService roleAndPermissionService;

    @PostMapping("/function")
    public JsonResonse insertPermissionFunction(@RequestBody Function function){
        JsonResonse resonse = new JsonResonse();
        try{
            roleAndPermissionService.insertPermissionFunction(function);
        }catch (Exception e){
            resonse.setCode(ErrorCode.FUNCTION_INSERT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.FUNCTION_INSERT_ERROR.getMessage());
        }
        return resonse;
    }

    @PutMapping("/function")
    public JsonResonse updatePermissionFunction(@RequestBody Function function){
        JsonResonse resonse = new JsonResonse();
        try{
            roleAndPermissionService.updatePermissionFunction(function);
        }catch (Exception e){
            resonse.setCode(ErrorCode.FUNCTION_UPDATE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.FUNCTION_UPDATE_ERROR.getMessage());
        }
        return resonse;
    }

    @DeleteMapping("/function")
    public JsonResonse deletePermissionFunction(@RequestParam("id") Integer id){
        JsonResonse resonse = new JsonResonse();
        try{
            roleAndPermissionService.deletePermissionFunction(id);
        }catch (Exception e){
            resonse.setCode(ErrorCode.FUNCTION_DELETE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.FUNCTION_DELETE_ERROR.getMessage());
        }
        return resonse;
    }

    @GetMapping("/permissionFunction")
    public JsonResonse selectPermissionFunction(@RequestParam(value = "id", required = false) Integer id,
                                                @RequestParam(value = "pageNum") Integer pageNum,
                                                @RequestParam(value = "pageSize") Integer pageSize){
        JsonResonse resonse = new JsonResonse();
        try{
            PageInfo<Function> functions = roleAndPermissionService.selectPermissionFunction(id, pageNum, pageSize);
            resonse.setData(functions);
        }catch (Exception e){
            resonse.setCode(ErrorCode.FUNCTION_SELECT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.FUNCTION_SELECT_ERROR.getMessage());
        }
        return resonse;
    }

    @PostMapping("/permission")
    public JsonResonse insertPermission(@RequestBody Permission permission){
        JsonResonse resonse = new JsonResonse();
        try{
            roleAndPermissionService.insertPermission(permission);
        }catch (Exception e){
            resonse.setCode(ErrorCode.PERMISSION_INSERT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.PERMISSION_INSERT_ERROR.getMessage());
        }
        return resonse;
    }

    @PutMapping("/permission")
    public JsonResonse updatePermission(@RequestBody Permission permission){
        JsonResonse resonse = new JsonResonse();
        try{
            roleAndPermissionService.updatePermission(permission);
        }catch (Exception e){
            resonse.setCode(ErrorCode.PERMISSION_UPDATE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.PERMISSION_UPDATE_ERROR.getMessage());
        }
        return resonse;
    }

    @DeleteMapping("/permission")
    public JsonResonse deletePermission(@RequestParam("id") Integer id){
        JsonResonse resonse = new JsonResonse();
        try{
            roleAndPermissionService.deletePermission(id);
        }catch (Exception e){
            resonse.setCode(ErrorCode.PERMISSION_DELETE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.PERMISSION_DELETE_ERROR.getMessage());
        }
        return resonse;
    }

    @GetMapping("/history")
    public JsonResonse selectHistory(){
        JsonResonse resonse = new JsonResonse();
        String roleName = RoleUtil.getRole();
        try{
            PermissionAndHistory permissionAndHistory = roleAndPermissionService.selectPermissionHistory(roleName);
            resonse.setData(permissionAndHistory);
        }catch (Exception e){
            resonse.setCode(ErrorCode.PERMISSION_HISTORY_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.PERMISSION_HISTORY_ERROR.getMessage());
        }
        return resonse;
    }

}
