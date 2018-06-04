package com.example.demo.dao;

import com.example.demo.domain.Function;
import com.example.demo.domain.Permission;
import com.example.demo.dto.Auths;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
    public List<Auths> findByAuth(String auth);

    void insertPermissionFunction(Function function);
    void updatePermissionFunction(Function function);
    void deletePermissionFunction(@Param("id") Integer id);
    List<Function> selectPermissionFunction(@Param("id") Integer id);

    void insertPermission(Permission permission);
    void updatePermission(Permission permission);
    void deletePermission(Integer id);

    List<Integer> selectPermissionHistory(@Param("name") String name);

}
