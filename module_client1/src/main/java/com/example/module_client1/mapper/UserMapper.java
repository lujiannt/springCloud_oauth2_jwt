package com.example.module_client1.mapper;

import com.example.module_client1.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User selectUserByName(String name);
}
