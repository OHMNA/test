package com.ling.web;

import com.ling.pojo.User;
import com.ling.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
    private UserService service=new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取复选框的数据
        String remember = request.getParameter("remember");


        //调用service层查询
        User user = service.login(username, password);

        //进行判断
        if(user != null){
            //判断用户是否勾选记住密码
            if ("1".equals(remember)){
                //发送cookie
                //1.创建cookie
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);

                //设置cookie的存活时间
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);
                //发送cookie
                response.addCookie(c_username);
                response.addCookie(c_password);
            }


            //将登录成功后的user储存到session中
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            //登录成功：重定向到成功页面
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/main");

        }else {
            //登录失败：求转发到失败页面
            //储存错误信息在request中
            request.setAttribute("login_msg","用户名或密码错误");
            //跳转到login.jsp页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
