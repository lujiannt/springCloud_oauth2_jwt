package com.example.jwt2.utils;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

public class MainTest {

    public static void main(String[] args) {
        //填写token
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsib3JkZXIiXSwidXNlcl9uYW1lIjoiYWRtaW4iLCJzY29wZSI6WyJzZWxlY3QiXSwiZXhwIjoxNTY3NzgxNTM5LCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJqdGkiOiJlNWM5NDg0Ny1iOWE0LTQ0OGMtOWY1Ny0zYWU4Y2NmN2Y2NGMiLCJjbGllbnRfaWQiOiJjbGllbnQifQ.jVa_j-X65-4aSJmidBC2NAqJD7aLNwZ-0nWgfziIhNdmwN_XyETaIaUO6jHZuF4zHOWENgDAVJSBo7M6bxraDXyXQtUpmj3594uPSW_2xB0qoN-dPFgm0L9Us6sUiR9HStn2QdR66QoMvbb6iICXMwSw4kP74eHjIuuqkNbLLEedO-HxV05A6XTnUIrWDCdQ3HCBdBFfA1Vdi0G0dtBMxM_KqzhjWvF-IwrLmbBgt_RqkTMpQRWQlhRXLVhaNV4BkE3HveA6YCrCALxsBWzMcIJhu1Uuj6a_Q6rGHdRNGKbarAq_pGEHuAi3W5dyse70gcipJ7TyytOCiwSyINNtHA";
        Jwt jwt = JwtHelper.decode(token);
        System.err.println(jwt.toString());
    }
}
