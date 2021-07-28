package com.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
    //跳转到管理员登录页面
    @RequestMapping({"/","/admin"})
    public String toAdminLogin(){
        return "admin_login";
    }


    //通用的模板页面跳转代码//page_admin_index
    @RequestMapping("/page_{target}")
    public String commonJump(@PathVariable("target") String target){//target=admin_index
        return target;
    }

}
