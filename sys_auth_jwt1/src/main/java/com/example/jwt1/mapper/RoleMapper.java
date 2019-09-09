package com.example.jwt1.mapper;

import com.example.jwt1.entity.Role;
import com.example.jwt1.entity.Url;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> selectRoleByUserId(int userId);

    List<Url> selectUrlByRoleId(int roleId);

    Role selectRoleByName(String roleName);
}
