package com.example.auth.mapper;

import com.example.auth.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectUserByName(String name);
}
