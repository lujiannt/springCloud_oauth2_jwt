package com.example.enhancer.utils;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

public class MainTest {

    public static void main(String[] args) {
        //填写token
        String token = "" +
                "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJzZWxlY3QiXSwidXNlck5hbWUxMjMiOiJhZG1pbiIsImV4cCI6MTU2ODAzMzI2NSwiYXV0aG9yaXRpZXMiOlsiQURNSU4iXSwianRpIjoiODViN2M1YTMtM2ZlYy00ODNhLWI0M2YtZDgzZmRlMTZjYzI5IiwiY2xpZW50X2lkIjoiY2xpZW50In0.ap_8X2mB6EUEBZr5kGFxz9jkZMwmF74wzEAFi_Zv0sc5i3LCycHpPoDpOZewsnIvek8Mws-8N3_S52_utYvnlp4wshP8RAJAZL2XtjphyVJOxkn6S9Hjo8EI56HS7RNFMOZvu6THfI4PaAR4rqnKSuR44O8dHyrRhC0luuCP-B146SpZlw6yZLMVrs9KR6xBAEd-LPA7kRSBaBgExe6OUgPWQ9graoggqleNV14EBZRuveJtdhWPKuy3A6MslFs-zUks4TlT1-Lz_di2CkKQLCTWgCt6_NdRQK-Cd1uXYieN9wWt6MV2DiRsPaynx0JIPRo-t73F9bGs3IeIwSskGw" +
                "";
        Jwt jwt = JwtHelper.decode(token);
        System.err.println(jwt.toString());
    }
}
