package com.example.demo.mapper;

import com.example.demo.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    void insertPermission(Permission permission);
    void updatePermission(@Param("id")Integer id, @Param("name")String name, @Param("url")String url, @Param("method")String method, @Param("lastModifiedBy")String lastModifiedBy, @Param("status")String status);
    void deletePermission(@Param("id")Integer id, @Param("status")String status);

    Integer checkResExists(@Param("url") String url, @Param("method") String method, @Param("status")String status);
    Permission selectPermissionOfCodePos(@Param("url") String url, @Param("method") String method, @Param("status")String status);
    Integer selectSystemMaxPos(@Param("status")String status);
    Integer selectSystemMaxCode(@Param("maxPos") Integer maxPos, @Param("status")String status);

    List<Integer> selectUserIdOfPermission(@Param("permissionId") Integer permissionId, @Param("status")String status);

    List<Permission> selectPermission(@Param("status")String status);
    Permission selectPermissionOfOne(@Param("permissionId") Integer permissionId, @Param("status")String status);

}
