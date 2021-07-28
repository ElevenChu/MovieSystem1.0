package com.movie.controller;

import com.movie.bean.Admin;
import com.movie.service.AdminService;
import com.movie.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    //异步请求登录
    @ResponseBody//将返回值自动转为JSON格式
    @RequestMapping(value = "/admin_login",method = RequestMethod.POST)
    public CommonResult login(Admin admin, String userCode, HttpServletResponse response, HttpSession session){
    CommonResult result=null;
        //系统验证码
        String safeCode = (String) session.getAttribute("safeCode");
        if (userCode.equalsIgnoreCase(safeCode)){
            Admin loginAdmin = adminService.login(admin);
            if (loginAdmin!=null){
                //存储session
                session.setAttribute("loginAdmin",loginAdmin);
                result=new CommonResult(200,"登陆成功");

            }else{
                result=new CommonResult(500,"登陆失败,账号或密码错误！！！");
            }
        }else{
                result=new CommonResult(150,"验证码输入错误");
        }

        return result;
    }

    @ResponseBody//将返回值自动转为JSON格式
    @RequestMapping(value = "/admin_logout",method = RequestMethod.POST)
    public CommonResult logout(HttpSession session){
        session.invalidate();
        CommonResult result=new CommonResult(200,"注销成功");
        return result;
    }

}
