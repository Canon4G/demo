package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.manager.UserManager;
import com.example.demo.model.User;
import com.example.demo.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by Canon4G 2018-12-13
 **/
@Controller
@RequestMapping("")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/")
    public String main(){
        return "redirect:/skipToLogin";
    }

    @RequestMapping(value = "/skipToLogin", method = RequestMethod.GET)
    public String skipToLogin() {
        return "login.html";
    }

    /**
     * 用户登录验证
     * @author Canon4G
     * @param username      用户名
     * @param password      密码
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();
        // 参数校验
        if (StringUtils.isBlank(username)) {
            result.put("returnMsg", "用户名不能为空!");
            return JsonResult.asFalseModel(result);
        }
        // 判断用户是否存在
        User user = userManager.getUserInfo(new User.Builder().userName(username).build());
        if (null == user) {
            result.put("returnMsg", "该用户不存在！");
            return JsonResult.asFalseModel(result);
        }
        // 判断密码是否正确
        if (!password.equals(user.getPassWord())){
            result.put("returnMsg", "密码错误！");
            return JsonResult.asFalseModel(result);
        }
        // 登录成功
        result.put("returnMsg", "登录成功！");
        return JsonResult.asTrueModel(result);
    }

    /**
     * 用户注册功能
     * @author Canon4G
     * @param username      用户名
     * @param password      密码
     * @param rePassword    确认密码
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult register(@RequestParam String username, @RequestParam String password, @RequestParam String rePassword) {
        Map<String, Object> result = new HashMap<>();
        // 参数校验

        // 判断用户是否存在
        User user = userManager.getUserInfo(new User.Builder().userName(username).build());
        if (null != user) {
            result.put("returnMsg", "用户名已存在！");
            return JsonResult.asFalseModel(result);
        }
        // 判断两次密码是否一致
        if (!rePassword.equals(password)) {
            result.put("returnMsg", "两次密码不一致！");
            return JsonResult.asFalseModel(result);
        }

        // TODO: 生成唯一的用户编号

        // 对密码进行加密并存入数据
        new User.Builder().userName(username).passWord(MD5Utils.MD5Encode(password,"utf8")).gmtCreate(new Date()).build();

        // TODO: 生成相关联的账户信息

        return null;
    }
}