package com.ling.json;

import com.alibaba.fastjson.JSON;

public class JsonDemo01 {


    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("陈炳林");
        //java对象转为json字符串
        String s = JSON.toJSONString(user);
        System.out.println(s);

        //将json字符串转为java对象
        User user1 = JSON.parseObject("{\"id\":1,\"name\":\"陈炳林\"}", User.class);
        System.out.println(user1);

    }
}
