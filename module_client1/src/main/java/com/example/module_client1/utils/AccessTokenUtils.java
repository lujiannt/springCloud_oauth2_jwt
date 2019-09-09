package com.example.module_client1.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class AccessTokenUtils {
    @Autowired
    private RedisUtils redisUtils;
    private static RedisUtils redisUtil;

    @PostConstruct
    public void setService() {
        redisUtil = redisUtils;
    }

    /**
     * 获取随机access_token
     *
     * @return
     */
    public static String getRandomToken() {
        Set<String> keys = redisUtil.keys("*");
        if (keys.size() > 0) {
            for (String key : keys) {
                if (key.indexOf("access:") == 0) {
                    String token = key.replace("access:", "");
                    return token;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String access = "access:1d298663-fe9f-4861-b2a0-b9706d6337c2";
        String res = access.replace("access:", "");
        System.out.println(access);
        System.out.println(res);

        getRandomToken();
    }
}
