package com.example.demo.controller;

import com.example.demo.domain.Function;
import com.example.demo.domain.Permission;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.RoleAndPermissionService;
import com.example.demo.util.JsonResonse;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleAndPermissionController {

    @Autowired
    private RoleAndPermissionService roleAndPermissionService;

    @PostMapping("/PermissionFunction")
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

    @PutMapping("/PermissionFunction")
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

    @DeleteMapping("/PermissionFunction")
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

    @GetMapping("/PermissionFunction")
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

    @PostMapping("/Permission")
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

    @PutMapping("/Permission")
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

    @DeleteMapping("/Permission")
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

}
