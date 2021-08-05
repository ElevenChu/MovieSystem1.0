package com.movie.controller;

import com.movie.bean.Users;
import com.movie.service.UsersService;
import com.movie.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    /**
     * 异步更新信息
     * @param users
     * @return
     */
    @ResponseBody
    @RequestMapping("/user_updateInfomation")
    public CommonResult user_updateInfomation(Users users,HttpSession session){
        System.out.println("要更新的对象是:"+users);
        CommonResult result;
        int count = usersService.user_updateInfomation(users);
        if(count>0){
            //把更新成功的对象查询一下
            Users u = usersService.findById(users.getUser_id());
            session.setAttribute("loginUser",u);
            result=new CommonResult(200,"用户更新信息成功");
        }else{
            result=new CommonResult(500,"用户更新信息失败");
        }
        return result;
    }

    /**
     * 修改头像
     */
    @RequestMapping("/user_updatePhoto")
    public void user_updatePhoto(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=utf-8");
        try {
            //要上传的原始的文件名
            String filename = file.getOriginalFilename();
            System.out.println("要上传的原始的文件名是:"+filename);  // 本人之死.jpg
            if(!"".equals(filename)){
                String fileKuozhanming = filename.substring(filename.lastIndexOf("."));
                System.out.println("要上传的文件的扩展名:"+fileKuozhanming);
                //生成一个新的永远不重复的文件名
                String newFileName= UUID.randomUUID().toString()+fileKuozhanming;
                System.out.println("生成一个新的永远不重复的文件名："+newFileName);
                //获取真实的文件上传的服务器路径
                String realPath=request.getServletContext().getRealPath("/photo")+"/"+newFileName;
                //E:\Server\MovieSystem\target\MovieSystem\photo/a43d6dfd-327a-4ec4-a3a5-6449230301f8.jpg
                System.out.println("上传的地址是:"+realPath);
                //开始文件上传
                file.transferTo(new File(realPath));
                System.out.println("文件上传成功~~~");
                HttpSession session= request.getSession();
                Users loginuser= (Users)session.getAttribute("loginUser");
                // 数据库的头像值的需要更新
                int count=usersService.updatePhoto(loginuser.getUser_id(),"/photo/"+newFileName);
                if(count>0){
                    //更新Session中数据
                    Users u = usersService.findById(loginuser.getUser_id());
                    session.setAttribute("loginUser",u);
                    response.getWriter().write("<script>alert('头像更新成功');location.href='/user_topersonal';</script>");
                }else{
                    response.getWriter().write("<script>alert('头像更新异常');location.href='/user_topersonal';</script>");
                }
            }else{
                response.getWriter().write("<script>alert('请选择要上传的头像');location.href='/user_topersonal';</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('头像更新异常');location.href='/user_topersonal';</script>");
        }
    }

}
