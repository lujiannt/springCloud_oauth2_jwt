package com.example.jwt2.mapper;

import com.example.jwt2.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectUserByName(String name);
}
