package com.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    @RequestMapping({"/","/admin"})
    public String toAdminLogin(){
        return "admin_login";
    }
}
