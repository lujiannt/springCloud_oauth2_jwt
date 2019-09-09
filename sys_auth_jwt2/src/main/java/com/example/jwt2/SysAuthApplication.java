package com.example.jwt2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.jwt2.mapper")
public class SysAuthApplication {


    public static void main(String[] args) {
        SpringApplication.run(SysAuthApplication.class, args);
    }

}
