package com.ling.cookie;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/ck01")
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建cookie
        //Cookie cookie = new Cookie("username", "wsh");
        String value="汪始慧";
        //对中文进行url编码
        value=URLEncoder.encode(value,"UTF-8");
        System.out.println("存储数据"+value);

        Cookie cookie = new Cookie("username", value);
        //设置cookie的存活时间
         cookie.setMaxAge(60 * 60 * 24 * 7);
        //2.发送cookie
        response.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
