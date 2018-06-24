package com.example.demo.mapper;

import com.example.demo.domain.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    List<SysRole> selectRoleDeeply(@Param("status")String status);
    List<SysRole> selectRoleDeeplyOfId(@Param("roleIdList") List<Integer> roleIdList, @Param("status")String status);
    SysRole selectRoleDeeplyOfOne(@Param("id") Integer id, @Param("status") String status);

    void insertRole(SysRole sysRole);
    void updateRole(@Param("id")Integer id, @Param("name")String name, @Param("description")String description, @Param("lastModifiedBy")String lastModifiedBy, @Param("status")String status);
    void deleteRole(@Param("id") Integer id, @Param("status")String status);

    Integer roleCount(String name);

    void deleteOldRelationship(Integer roleId);
    void deleteOldRelationshipOfPermission(Integer permissionId);
    void deleteOldRelationshipOfRole(Integer roleId);
    void saveNewRelationship(@Param("roleId") Integer roleId, @Param("permissionIdList") List<Integer> permissionIdList);
    List<Integer> selectUserIdOfRoleId(@Param("roleId") Integer roleId);

}
