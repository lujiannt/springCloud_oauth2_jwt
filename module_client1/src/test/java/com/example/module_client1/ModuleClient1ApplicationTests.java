package com.example.module_client1;

import com.example.module_client1.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleClient1ApplicationTests {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void contextLoads() {
        Set<String> keys = redisUtils.keys("*");

        System.out.println(keys.size());
    }

}
