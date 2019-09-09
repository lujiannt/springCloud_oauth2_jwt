package com.example.module_client2.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/demo")
    @ResponseBody
    public String demo() {
        return "client222 demo";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/securedPage")
    public String securedPage() {
        return "/securedPage";
    }
}
