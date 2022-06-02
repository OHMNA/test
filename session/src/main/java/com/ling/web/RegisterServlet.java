package com.ling.web;

import com.ling.pojo.User;
import com.ling.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService service=new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.对数据进行封装
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //3.调用service层注册
        boolean flag = service.register(user);
        //4.判断是否注册成功
        if (flag) {
            //注册成功，转发到登录页面
            request.setAttribute("register_msg","注册成功");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else {
            //注册失败，用户名已经存在
            request.setAttribute("register_msg","注册失败，用户名已经存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
