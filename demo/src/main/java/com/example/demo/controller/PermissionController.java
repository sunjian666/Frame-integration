package com.example.demo.controller;

import com.example.demo.domain.Permission;
import com.example.demo.dto.PermissionAndHistory;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.PermissionService;
import com.example.demo.util.JsonResonse;
import com.example.demo.util.RoleUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permission")
    public JsonResonse insertPermission(@RequestBody Permission permission, HttpServletRequest request){
        JsonResonse resonse = new JsonResonse();

        String url = permission.getUrl();
        String method = permission.getMethod();

        try{
            if(permissionService.checkResExists(url, method)){
                resonse.setCode(ErrorCode.PERMISSION_ALREADY_EXIST.getCode());
                resonse.setSuccess(false);
                resonse.setMessage(ErrorCode.PERMISSION_ALREADY_EXIST.getMessage());
                return resonse;
            }

            Permission permissionCode = permissionService.createPermissionCode(url, method);
            permissionCode.setName(permission.getName());
            permissionCode.setDescription(permission.getDescription());
            permissionCode.setFunctionId(permission.getFunctionId());

            permissionService.insertPermission(permission, request);
        }catch (Exception e){
            resonse.setCode(ErrorCode.PERMISSION_INSERT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.PERMISSION_INSERT_ERROR.getMessage());
        }
        return resonse;
    }

    @PutMapping("/permission")
    public JsonResonse updatePermission(@RequestBody Permission permission, HttpServletRequest request){
        JsonResonse resonse = new JsonResonse();
        try{
            permissionService.updatePermission(permission, request);
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
            permissionService.deletePermission(id);
        }catch (Exception e){
            resonse.setCode(ErrorCode.PERMISSION_DELETE_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.PERMISSION_DELETE_ERROR.getMessage());
        }
        return resonse;
    }

    @GetMapping("/permissionAll")
    public JsonResonse selectPermissionAll(@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        JsonResonse resonse = new JsonResonse();
        try{
            PageInfo<Permission> permissionPageInfo = permissionService.selectPermission(pageNum, pageSize);
            resonse.setData(permissionPageInfo);
        }catch (Exception e){
            resonse.setCode(ErrorCode.PERMISSION_SELECT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.PERMISSION_SELECT_ERROR.getMessage());
        }
        return resonse;
    }

    @GetMapping("/permissionOne")
    public JsonResonse selectPermissionOne(@RequestParam("id") Integer id){
        JsonResonse resonse = new JsonResonse();
        try{
            Permission permission = permissionService.selectPermissionOfOne(id);
            resonse.setData(permission);
        }catch (Exception e){
            resonse.setCode(ErrorCode.PERMISSION_SELECT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.PERMISSION_SELECT_ERROR.getMessage());
        }
        return resonse;
    }

}
