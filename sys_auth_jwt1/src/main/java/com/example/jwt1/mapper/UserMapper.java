package com.example.jwt1.mapper;

import com.example.jwt1.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectUserByName(String name);
}
