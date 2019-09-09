package com.example.auth.serviceImpl;

import com.example.auth.entity.Role;
import com.example.auth.entity.Url;
import com.example.auth.mapper.RoleMapper;
import com.example.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listRolesByUser(int userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<Url> listUrlsByRole(int roleId) {
        return roleMapper.selectUrlByRoleId(roleId);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleMapper.selectRoleByName(roleName);
    }

    @Override
    public String update() {
        System.out.println("execute update..");
        return "update";
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public String delete() {
        System.out.println("execute delete..");
        return "delete";
    }
}
