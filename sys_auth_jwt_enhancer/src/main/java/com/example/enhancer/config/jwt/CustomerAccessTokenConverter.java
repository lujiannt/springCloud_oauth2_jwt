package com.example.enhancer.config.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义token转换器
 * 【注意】转换器用于token的生成、转换、获取，这里只重写了转换方法用于添加自定义消息
 */
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {

    public CustomerAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            response.put("details", authentication.getDetails());
            response.put("test", "hello-test");
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }
            return response;
        }
    }

}
