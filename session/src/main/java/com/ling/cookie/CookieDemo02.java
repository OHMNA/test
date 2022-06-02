package com.ling.cookie;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/ck02")
public class CookieDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取客户端携带的所有cookie，使用request对象
        Cookie[] cookies = request.getCookies();
        //遍历数组获取每一个cookie对象
        for (Cookie cookie:cookies){
            //使用Cookie的对象方法获取数据
            String name = cookie.getName();
            if ("username".equals(name)){
                String value = cookie.getValue();

                //URL解码
                value = URLDecoder.decode(value, "UTF-8");
                System.out.println(name+":"+value);//解码后为汪始慧
            }
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
