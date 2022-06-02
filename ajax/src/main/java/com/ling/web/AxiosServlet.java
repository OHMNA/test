package com.ling.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/axiosServlet")
public class AxiosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get方法。。。");
        //获取前端请求参数
        String username = request.getParameter("username");

        System.out.println(username);
        //响应前端数据
        response.getWriter().write("I am ohmnanon");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("pot方法。。。");
        doGet(request, response);
    }
}
