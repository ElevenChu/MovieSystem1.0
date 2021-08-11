package com.movie.interceptor;

import com.movie.bean.Admin;
import com.movie.bean.Users;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: MovieSystem
 * @BelongsPackage: com.movie.interceptor
 * @CreateTime:
 * @Description: 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * ---->该方法在目标方法之前被调用
     * 若返回值为true,则继续调用后去的拦截器和目标方法
     * 若返回值为false,则不会再调用后续的拦截器和方法
     * 可以考虑做权限.日志,事务,等.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在此处完成权限的拦截
        String requestURI = request.getRequestURI();
        System.out.println("访问地址:"+requestURI);
        Admin loginAdmin=(Admin) request.getSession().getAttribute("loginAdmin");
        if(loginAdmin!=null){
            return true;
        }else{
            if(requestURI.startsWith("/pay_")||requestURI.startsWith("/buy_")||requestURI.startsWith("/user_")){
                Users loginUser=(Users) request.getSession().getAttribute("loginUser");
                if(loginUser==null){
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().write("<script>alert('请先登录网站');location.href='/index';</script>");
                    return false;
                }else {
                    return true;
                }
            }else{
                response.sendRedirect("/admin");
                return false;
            }
        }
    }

    /**
     * ----->调用目标方法之后,但渲染视图之前.
     * 有modelAndView对象,可以对请求域中的属性或视图做出修改.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * ------>在整个请求结束后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
