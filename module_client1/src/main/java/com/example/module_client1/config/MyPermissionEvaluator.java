package com.example.module_client1.config;

import com.example.module_client1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * 自定义权限过滤器
 */
@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private RoleService roleService;

    /**
     * 通过请求过来的url和数据库中的角色对应url来对应校验权限
     *
     * @param authentication
     * @param targetUrl
     * @param targetPermission
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        System.out.println("第一个hasPermission : targetUrl : " + targetUrl);
        System.out.println("第一个hasPermission : targetPermission : " + targetPermission);

        if (authentication.isAuthenticated()) {
            String username = (String) authentication.getPrincipal();

            //TODO 自定义代码，从数据校验用户权限
            if (targetUrl.equals("/role1")) {
                if (username.equals("lj")) {
                    return false;
                } else if (username.equals("admin")) {
                    return true;
                }
            } else {
                return true;
            }

        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object targetPermission) {
        System.out.println("第二个hasPermission： targetId=" + targetId);
        System.out.println("第二个hasPermission： targetType=" + targetType);
        System.out.println("第二个hasPermission： targetPermission=" + targetPermission);
        return false;
    }
}
