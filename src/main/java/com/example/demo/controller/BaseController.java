package com.example.demo.controller;

import com.example.demo.manager.UserManager;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * create by Canon4G 2018-12-16
 **/
public class BaseController {

    @Autowired
    UserManager userManager;

    /**
     * 根据session获取当前登录用户信息
     * @author guanhao
     * @param request   request
     * @return User
     */
    public User checkUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        return user;
    }
}
