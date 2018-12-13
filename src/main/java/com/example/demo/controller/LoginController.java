package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * create by guanhao 2018-12-13
 **/
@Controller
@RequestMapping("")
public class LoginController {

    // 我是真的帅
    @RequestMapping(value = "/")
    public String main(){
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login.html";
    }

    /**
     * 用户登录验证
     * @author guanhao
     * @param username      用户名
     * @param password      密码
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public JsonResult toLogin(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();

        return null;
    }
}
