package com.movie.controller;

import com.movie.bean.Users;
import com.movie.service.UsersService;
import com.movie.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UsersController {
    @Autowired
    UsersService usersService;

    /**
     * 01-异步请求登录接口 必须以post请求
     * @return
     */
    @ResponseBody //把返回值自动转为JSON格式！
    @RequestMapping(value ="/user_login" ,method = RequestMethod.POST)
    public CommonResult login(String user_code, Users users, HttpServletResponse response, HttpSession session) throws Exception{
        System.out.println("要登录的对象是："+users);
        CommonResult result=null;
        String safeCode=(String) session.getAttribute("safeCode"); //系统验证码
        if(safeCode.equalsIgnoreCase(user_code)){
            Users loginUser = usersService.user_login(users);
            if(loginUser!=null){
                result=new CommonResult(200,"登录成功！");
                //存储的session
                session.setAttribute("loginUser",loginUser);
            }else{
                result=new CommonResult(500,"账户或密码错误！");
            }
        }else{
            result=new CommonResult(250,"验证码错误！");
        }
        return result;
    }

    /**
     * 注册
     * user_registry与user_register注意
     */
    @ResponseBody
    @RequestMapping(value = "/user_register",method = RequestMethod.POST)
    public CommonResult user_register(Users users){
        System.out.println("要注册的对象是:"+users);
        CommonResult result;
        //用户默认头像
        users.setImg_url("/photo/defaultPhoto.jpg");
        int count = usersService.user_registry(users);
        if(count>0){
            result=new CommonResult(200,"用户注册成功");
        }else{
            result=new CommonResult(500,"用户注册失败");
        }
        return result;
    }
    /**
     * 跳转个人中心
     * @return
     */
    @RequestMapping("/user_topersonal")
    public String user_topersonal(){
        System.out.println("查询数据....");
        return "user_personal";
    }
}
