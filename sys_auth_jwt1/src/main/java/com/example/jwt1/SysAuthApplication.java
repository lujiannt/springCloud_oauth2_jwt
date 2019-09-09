package com.example.jwt1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableResourceServer
@MapperScan("com.example.jwt1.mapper")
public class SysAuthApplication {


    public static void main(String[] args) {
        SpringApplication.run(SysAuthApplication.class, args);
    }

}
