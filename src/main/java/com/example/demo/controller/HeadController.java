package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.manager.UserAccountManager;
import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 主页面头部
 * create by Canon4G 2018-12-18
 **/
@Controller
@RequestMapping(value = "head/")
public class HeadController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(HeadController.class);

    @Autowired
    UserAccountManager userAccountManager;

    @ResponseBody
    @RequestMapping(value = "init", method = RequestMethod.POST)
    public JsonResult init(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = checkUserSession(request);
        if (null == user) {
            return JsonResult.asFalse();
        }
        // 用户名
        String userName = user.getUserName();
        // 用户权限
        String isAdmin = user.getIsAdmin();
        // 通过用户编号查询账户信息
        UserAccount userAccountInfo = userAccountManager.getUserAccountInfo(new UserAccount.Builder().userCode(user.getUserCode()).build());
        // 账户余额
        BigDecimal accountMoney = (null == userAccountInfo.getAccountMoney()) ? new BigDecimal("0.00") : userAccountInfo.getAccountMoney().setScale(2, BigDecimal.ROUND_HALF_UP);
        result.put("userName", userName);
        result.put("accountMoney", accountMoney);
        result.put("isAdmin", isAdmin);
        return JsonResult.asTrueModel(result);
    }
}
