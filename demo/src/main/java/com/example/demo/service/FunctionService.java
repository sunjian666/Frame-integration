package com.example.demo.service;

import com.example.demo.domain.Function;
import com.github.pagehelper.PageInfo;

public interface FunctionService {

    void insertPermissionFunction(Function function);
    void updatePermissionFunction(Function function);
    void deletePermissionFunction(Integer id);
    PageInfo<Function> selectPermissionFunction(Integer id, Integer pageNum, Integer pageSize);
    Integer functionCount(String name);

}
