package com.example.demo.mapper;

import com.example.demo.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserDao {

    SysUser findByUserName(@Param("username")String username, @Param("status") String status);

    String selectCodeArr(String userName);

    void insertUser(SysUser user);

    Integer countUser(String username);

    void updateUser(@Param("password") String password, @Param("name") String name, @Param("email") String email, @Param("phone") String phone, @Param("lastModifiedBy") String lastModifiedBy, @Param("id") Integer id, @Param("status") String status);

    SysUser selectUserOne(@Param("id") Integer id, @Param("status") String status);

    List<SysUser> selectUserAll(String status);

    void deleteUser(@Param("id") Integer id, @Param("status") String status);

    void deleteOldRelationship(Integer userId);

    void saveNewRelationshipOfUser(@Param("userId") Integer userId, @Param("roleIdList") List<Integer> roleIdList);

    void updateCodeArr(@Param("userId") Integer userId, @Param("codeArr") String codeArr, @Param("status") String status);

}
