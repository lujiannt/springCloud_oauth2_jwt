package com.example.enhancer.config.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义tokenEnhancer携带用户信息
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> map = new HashMap<>();
        //往jwt token中添加自定义消息
        UserDetails userDetails = (UserDetails) oAuth2Authentication.getUserAuthentication().getPrincipal();
        map.put("userName123", userDetails.getUsername());
        DefaultOAuth2AccessToken accessToken = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        accessToken.setAdditionalInformation(map);
        return accessToken;
    }
}
