package com.example.demo.controller;

import com.example.demo.domain.Function;
import com.example.demo.domain.Permission;
import com.example.demo.dto.PermissionAndHistory;
import com.example.demo.exception.ErrorCode;
import com.example.demo.service.FunctionService;
import com.example.demo.service.PermissionService;
import com.example.demo.util.JsonResonse;
import com.example.demo.util.RoleUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @PostMapping("/function")
    public JsonResonse insertPermissionFunction(@RequestBody Function function){
        JsonResonse resonse = new JsonResonse();

        String name = function.getName();

        try{
            Integer functionCount = functionService.functionCount(name);
            if (functionCount > 0){
                resonse.setCode(ErrorCode.FUNCTION_ALREADY_EXIST.getCode());
                resonse.setSuccess(false);
                resonse.setMessage(ErrorCode.FUNCTION_ALREADY_EXIST.getMessage());
                return resonse;
            }
            functionService.insertPermissionFunction(function);
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
            functionService.updatePermissionFunction(function);
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
            functionService.deletePermissionFunction(id);
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
            PageInfo<Function> functions = functionService.selectPermissionFunction(id, pageNum, pageSize);
            resonse.setData(functions);
        }catch (Exception e){
            resonse.setCode(ErrorCode.FUNCTION_SELECT_ERROR.getCode());
            resonse.setSuccess(false);
            resonse.setMessage(ErrorCode.FUNCTION_SELECT_ERROR.getMessage());
        }
        return resonse;
    }

}
