package com.example.demo.service;

import com.example.demo.domain.Function;
import com.example.demo.mapper.FunctionDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService{

    @Autowired
    private FunctionDao functionDao;

    @Override
    public void insertPermissionFunction(Function function) {
        functionDao.insertPermissionFunction(function);
    }

    @Override
    public void updatePermissionFunction(Function function) {
        functionDao.updatePermissionFunction(function);
    }

    @Override
    public void deletePermissionFunction(Integer id) {
        functionDao.deletePermissionFunction(id);
    }

    @Override
    public PageInfo<Function> selectPermissionFunction(Integer id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Function> functions = functionDao.selectPermissionFunction(id);
        PageInfo<Function> pageInfo = new PageInfo<>(functions);
        return pageInfo;
    }

    @Override
    public Integer functionCount(String name) {
        return functionDao.functionCount(name);
    }

}
