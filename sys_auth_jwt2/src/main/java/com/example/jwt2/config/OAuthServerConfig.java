package com.example.jwt2.config;

import com.example.jwt2.serviceImpl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * 授权服务器
 */
@Configuration
public class OAuthServerConfig {
    private static final String DEMO_RESOURCE_ID = "order";

    /**
     * 资源配置中心
     */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        RedisConnectionFactory redisConnectionFactory;
        @Autowired
        UserDetailServiceImpl userDetailsServiceImpl;

        /**
         * 动态切换tokenStore
         *
         * @return
         */
        @Bean
        public TokenStore myTokenStore() {
            //使用jwt方式
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
            //允许表单认证
            oauthServer.allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            //持多种编码，通过密码的前缀区分编码方式
            String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");

            //配置客户端 【注意】客户端模块yml中也要有对应配置
            clients.inMemory().withClient("client")
                    .secret(finalSecret)
                    .resourceIds(DEMO_RESOURCE_ID)
                    //授权模式
                    .authorizedGrantTypes("authorization_code")
                    .scopes("select")
                    .autoApprove(true)
                    //配置重定向url
                    .redirectUris("http://localhost:8081/login", "http://baidu.com", "http://localhost:8082/login").autoApprove(true);
        }


        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            //指定认证管理器
            endpoints.authenticationManager(authenticationManager);
            //指定token存储方式
            endpoints.tokenStore(myTokenStore());
            // token生成方式
            endpoints.accessTokenConverter(jwtAccessTokenConverter());
            endpoints.userDetailsService(userDetailsServiceImpl);
        }

        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            //加载证书
            KeyStoreKeyFactory keyStoreKeyFactory =
                    new KeyStoreKeyFactory(new ClassPathResource("mytest.jks"), "mypass".toCharArray());
            accessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));

            return accessTokenConverter;
        }
    }
}
