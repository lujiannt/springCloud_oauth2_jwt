package com.example.auth.serviceImpl;

import com.example.auth.entity.Role;
import com.example.auth.mapper.RoleMapper;
import com.example.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义UserDetailsService 从数据库读取用户信息 且 校验
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.example.auth.entity.User user = userMapper.selectUserByName(userName);
        if (user == null) {
            throw new RuntimeException("user is null");
        }

        // 添加角色
        List<Role> roles = roleMapper.selectRoleByUserId(user.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(roles.get(i).getRoleName()));
        }

        User myUser = new User(user.getUsername(), user.getPassword(), authorities);
        return myUser;
    }
}
