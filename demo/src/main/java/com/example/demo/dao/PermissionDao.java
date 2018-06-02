package com.example.demo.dao;

import com.example.demo.domain.Permission;
import com.example.demo.dto.Auths;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
    public List<Auths> findByAuth(String auth);

}
