package com.example.jwt2.service;

import com.example.jwt2.entity.Role;
import com.example.jwt2.entity.Url;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface RoleService {
    /**
     * 根据用户id获取该用户角色
     *
     * @param userId
     * @return
     */
    List<Role> listRolesByUser(int userId);

    /**
     * 根据角色id获取可访问所有资源
     *
     * @param roleId
     * @return
     */
    List<Url> listUrlsByRole(int roleId);

    /**
     * 根据角色名查询角色
     *
     * @param roleName
     * @return
     */
    Role getRoleByName(String roleName);

    /**
     * service层权限测试接口
     *
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    String update();

    /**
     * service层权限测试接口
     *
     * @return
     */
    String delete();
}
