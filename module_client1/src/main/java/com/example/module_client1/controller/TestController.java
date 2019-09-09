package com.example.module_client1.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        return "client111";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/securedPage")
    public String securedPage() {
        return "/securedPage";
    }

    /**
     * 权限测试1
     *
     * @return
     */
    @RequestMapping("/role1")
    @ResponseBody
    @PreAuthorize("hasPermission('/role1','ADMIN')")
    public String role1() {
        return "ROLE : ADMIN";
    }

    /**
     * 权限测试2
     *
     * @return
     */
    @RequestMapping("/role2")
    @ResponseBody
    @PreAuthorize("hasPermission('/role2','USER')")
    public String role2() {
        return "ROLE : USER";
    }

    /**
     * 跳转到自定义权限403页面
     *
     * @return
     */
    @RequestMapping("/403")
    public String forbidden() {
        return "/403";
    }

    /**
     * 登出后页面
     *
     * @return
     */
    @RequestMapping("/exit")
    public String exit() {
        return "/exit";
    }
}
