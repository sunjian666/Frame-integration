package com.example.demo.mapper;

import com.example.demo.domain.Function;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionDao {

    void insertPermissionFunction(Function function);
    void updatePermissionFunction(Function function);
    void deletePermissionFunction(@Param("id") Integer id);
    List<Function> selectPermissionFunction(@Param("id") Integer id);
    Integer functionCount(String name);

}
