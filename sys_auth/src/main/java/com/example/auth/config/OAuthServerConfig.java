package com.example.auth.config;

import com.example.auth.serviceImpl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

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
            return new RedisTokenStore(redisConnectionFactory);
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


        /*------------------------------------------- redis ----------------------------------------------*/

        /**
         * 将token存储到redis
         *
         * @param endpoints
         */
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints
                    .tokenStore(myTokenStore())
                    .authenticationManager(authenticationManager)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        }
    }
}
