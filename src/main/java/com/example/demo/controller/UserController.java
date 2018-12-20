package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.enums.UserIsAdmin;
import com.example.demo.manager.UserManager;
import com.example.demo.model.User;
import com.example.demo.util.MD5Utils;
import com.example.demo.util.MyPage;
import com.example.demo.util.UUIDHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by Canon4G 2018-12-13
 **/
@Controller
@RequestMapping("")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final Integer PAGESIZE = 10;

    @Autowired
    UserManager userManager;

    /**
     * 用户登录验证
     * @author Canon4G
     * @param username 用户名
     * @param password 密码
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(HttpSession session, @RequestParam String username, @RequestParam String password) {
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
        password = MD5Utils.MD5Encode(password,"utf8");
        // 判断密码是否正确
        if (!password.equals(user.getPassWord())) {
            result.put("returnMsg", "密码错误！");
            return JsonResult.asFalseModel(result);
        }
        // 登录成功
        session.setAttribute("user", user);
        result.put("returnMsg", "登录成功！");
        return JsonResult.asTrueModel(result);
    }

    /**
     * 退出登录
     * @author guanhao
     * @param request   request
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public JsonResult logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return JsonResult.asTrue();
    }

    /**
     * 用户注册功能
     * @author Canon4G
     * @param username   用户名
     * @param password   密码
     * @param rePassword 确认密码
     * @param isAdmin    用户权限
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult register(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String rePassword,
                               @RequestParam String isAdmin) {
        Map<String, Object> result = new HashMap<>();
        // 参数校验
        if (StringUtils.isBlank(username)) {
            result.put("returnMsg", "用户名不能为空！");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtils.isBlank(password)) {
            result.put("returnMsg", "密码不能为空！");
            return JsonResult.asFalseModel(result);
        }
        if (6 > username.length()) {
            result.put("returnMsg", "用户名不能小于6位！");
            return JsonResult.asFalseModel(result);
        }
        if (6 > password.length()) {
            result.put("returnMsg", "密码不能小于6位！");
            return JsonResult.asFalseModel(result);
        }
        if (20 < username.length()) {
            result.put("returnMsg", "用户名不能超过20位！");
            return JsonResult.asFalseModel(result);
        }
        if (20 < password.length()) {
            result.put("returnMsg", "密码不能超过20位！");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtils.isBlank(isAdmin) || "-1".equals(isAdmin)) {
            result.put("returnMsg", "请选择正确的用户角色");
            return JsonResult.asFalseModel(result);
        }
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
        // 生成用户编码，并对对密码进行加密，进行注册
        userManager.addUserAndAccount(new User.Builder()
                .userCode(UUIDHelper.uuid())
                .userName(username)
                .passWord(MD5Utils.MD5Encode(password, "utf8"))
                .isAdmin(isAdmin)
                .gmtCreate(new Date())
                .build());
        result.put("returnMsg", "注册成功！");
        return JsonResult.asTrueModel(result);
    }

    /**
     * 根据角色名称获得角色值
     * @param isAdmin 角色名称
     * @return String
     */
    private String getAdminValue(String isAdmin) {
        if (UserIsAdmin.ADMIN.getDesc().equals(isAdmin)) {
            isAdmin = UserIsAdmin.ADMIN.getValue();
        } else if (UserIsAdmin.SUPER_ADMIN.getDesc().equals(isAdmin)) {
            isAdmin = UserIsAdmin.SUPER_ADMIN.getValue();
        } else if (UserIsAdmin.USER.getDesc().equals(isAdmin)) {
            isAdmin = UserIsAdmin.USER.getValue();
        }
        return isAdmin;
    }

    /**
     * 用户信息展示(分页)
     * @author Canon4G
     * @param userName         用户名称
     * @param userType         用户角色
     * @param pageNum          当前页码
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public JsonResult getUserList(@RequestParam String userName,
                                  @RequestParam String userType,
                                  @RequestParam int pageNum) {
        Map<String, Object> result = new HashMap<>();
        User user = new User.Builder().userName(userName).isAdmin(userType).build();
        MyPage<User> myPage = userManager.getUserInfoListPage(user, pageNum, PAGESIZE);
        result.put("list", myPage.getList());
        result.put("count", myPage.getCount());
        result.put("pageNum", pageNum);
        result.put("pageSize", PAGESIZE);
        long pageTotal = (myPage.getCount() + PAGESIZE - 1) / PAGESIZE;
        result.put("pageTotal", String.valueOf(pageTotal));
        return JsonResult.asTrueModel(result);
    }



    /**
     * 获得用户详情
     * @author Canon4G
     * @param userCode  用户编码
     * @return  JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "/getUserDetails", method = RequestMethod.POST)
    public JsonResult getUserDetails(@RequestParam String userCode) {
        Map<String, Object> result = new HashMap<>();
        // 参数校验
        if (StringUtils.isBlank(userCode)) {
            result.put("returnMsg", "修改用户信息失败");
            return JsonResult.asFalseModel(result);
        }
        User user = userManager.getUserInfo(new User.Builder().userCode(userCode).build());
        result.put("user", user);
        return JsonResult.asTrueModel(result);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public JsonResult updateUser(@RequestParam String userCode,
                                 @RequestParam String userName,
                                 @RequestParam String password2,
                                 @RequestParam String password3,
                                 @RequestParam String isAdmin,
                                 @RequestParam String isAdmin2) {
        Map<String, Object> result = new HashMap<>();
        // 参数校验
        if (StringUtils.isBlank(userName)) {
            result.put("returnMsg", "用户名不能为空!");
            return JsonResult.asFalseModel(result);
        }
        User user = userManager.getUserInfo(new User.Builder().userCode(userCode).build());
        if (!userName.equals(user.getUserName())) {
            User user2 = userManager.getUserInfo(new User.Builder().userName(userName).build());
            if (null != user2) {
                result.put("returnMsg", "用户名已存在!");
                return JsonResult.asFalseModel(result);
            }
        }
        if (6 > userName.length()) {
            result.put("returnMsg", "用户名不能小于6位！");
            return JsonResult.asFalseModel(result);
        }
        if (20 < userName.length()) {
            result.put("returnMsg", "用户名不能超过20位！");
            return JsonResult.asFalseModel(result);
        }
        if (!StringUtils.isBlank(password2)) {
            if (6 > password2.length()) {
                result.put("returnMsg", "密码不能小于6位！");
                return JsonResult.asFalseModel(result);
            }
            if (20 < password2.length()) {
                result.put("returnMsg", "密码不能超过20位！");
                return JsonResult.asFalseModel(result);
            }
        }
        // 判断两次密码是否一致
        if (!password3.equals(password2)) {
            result.put("returnMsg", "两次密码不一致！");
            return JsonResult.asFalseModel(result);
        }
        isAdmin = getAdminValue(isAdmin);
        isAdmin = (StringUtils.isBlank(isAdmin2) || "-1".equals(isAdmin2) || isAdmin2.equals(isAdmin)) ? isAdmin : isAdmin2;
        userManager.updateUser(new User.Builder()
                .userName(userName)
                .userCode(userCode)
                .passWord(MD5Utils.MD5Encode(password2, "utf8"))
                .isAdmin(isAdmin)
                .gmtModified(new Date())
                .build());
        result.put("returnMsg", "修改成功!");
        return JsonResult.asTrueModel(result);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public JsonResult deleteUser(@RequestParam String userCode) {
        Map<String, Object> result = new HashMap<>();
        // 参数校验
        if (StringUtils.isBlank(userCode)) {
            result.put("returnMsg", "删除用户信息失败");
            return JsonResult.asFalseModel(result);
        }
        userManager.deleteUser(userCode);
        result.put("returnMsg", "删除用户信息成功");
        return JsonResult.asTrueModel(result);
    }
}
