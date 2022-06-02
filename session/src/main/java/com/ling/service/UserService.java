package com.ling.service;

import com.ling.mapper.UserMapper;
import com.ling.pojo.User;
import com.ling.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    //1.使用工具类 调用sqlSessionFactory
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录的方法
     * @param username
     * @param password
     * return
     * */
    public User login(String username,String password){
        //2.获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用dao层的方法
        User user = mapper.select(username, password);
        //关闭资源
        sqlSession.close();

        return user;
    }

    /**
     * 注册的方法
     * @param user
     * return
     * */
    public boolean register(User user){
        //2.获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用dao层的方法
        //判断用户名是否存在
        User u = mapper.selectByUsername(user.getUsername());
        if (u == null){
            //如果用户名不存在就调用添加的方法
            mapper.add(user);
            //提交事务
            sqlSession.commit();
        }
        //资源回收
        sqlSession.close();
        return u == null;
    }
}
