package com.example.enhancer.mapper;

import com.example.enhancer.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectUserByName(String name);
}
