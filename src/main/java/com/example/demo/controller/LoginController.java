package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.manager.UserManager;
import com.example.demo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserManager userManager;

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
}
