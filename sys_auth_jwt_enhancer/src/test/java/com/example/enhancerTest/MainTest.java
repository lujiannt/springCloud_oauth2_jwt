package com.example.enhancerTest;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

public class MainTest {

    public static void main(String[] args) {
        //填写token
        String token = "" +
                "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidGVzdCI6ImhlbGxvLXRlc3QiLCJzY29wZSI6WyJzZWxlY3QiXSwidXNlck5hbWUxMjMiOiJhZG1pbiIsImRldGFpbHMiOnsicmVtb3RlQWRkcmVzcyI6IjA6MDowOjA6MDowOjA6MSIsInNlc3Npb25JZCI6IkM1MDA0NUQyNEY0OUM5MjcyNDRCQkEwNURERTQ3QzZBIn0sImV4cCI6MTU2ODAzNDQzOSwiYXV0aG9yaXRpZXMiOlsiQURNSU4iXSwianRpIjoiNDQwM2FjMmItODY3Ny00ZDEzLTkwMjktYzA1YTdlOWMzN2Q4IiwiY2xpZW50X2lkIjoiY2xpZW50In0.h4as_W5D4FTPXD9R8gevz6dKs4TIBcikgOjy-NZJgqD56MW41IkA9M4sGDQ-Y9FU_cVd8LlC-bREAe4L6hzY76yfJZatwmtPZchrXc5WySWpvLO-y95TTfrtpRvruOmn1Y30X3DUQh7Rrny9XgCmcFanmZ7LYo20sG3wslvvwRgOhJlU7QeEgXtnVs2DIFqHAp1QcEpg9R7FNuaco92RRFhNt4nNu0ChJ0ibVyU7yo2JfMqznoXs8kM-QIOCR0MwEN-74J4CR8MnctOlH5C7IAxGlKLNDyZ-c2NZGn2q0JKOhqpAG2cv574-0xtqAKlKev6z9Bg8Bci97cWcKyoqvg" +
                "";
        Jwt jwt = JwtHelper.decode(token);
        System.err.println(jwt.toString());
    }
}
