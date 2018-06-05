package com.example.demo.mapper;

import com.example.demo.domain.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserDao {

    public SysUser findByUserName(String username);

    public SysUser getById(Integer id);

}
