package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.manager.RechargeManager;
import com.example.demo.model.RechargeDetail;
import com.example.demo.model.User;
import com.example.demo.util.MyPage;
import com.example.demo.util.StringUtil;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * 充值
 * create by Canon4G 2018-12-16
 **/
@Controller
@RequestMapping(value = "recharge/")
public class RechargeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RechargeController.class);

    private static final Integer PAGESIZE = 10;

    @Autowired
    RechargeManager rechargeManager;

    /**
     * 为账户充值
     * @author Canon4G
     * @param request   request
     * @param money     充值金额
     * @param mode      充值方式
     * @return  JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "rechargeAccount", method = RequestMethod.POST)
    public JsonResult rechargeAccount(HttpServletRequest request, @RequestParam String money, @RequestParam String mode) {
        Map<String, Object> result = new HashMap<>();
        User user = checkUserSession(request);
        // 参数校验
        if (null == user) {
            result.put("returnMsg", "请您登录后再充值");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtils.isBlank(money)) {
            result.put("returnMsg", "充值金额不能为空");
            return JsonResult.asFalseModel(result);
        }
        if (!StringUtil.isNumericOrDecimal(money)) {
            result.put("returnMsg", "充值金额必须是数字型");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtils.isBlank(mode) || "-1".equals(mode)) {
            result.put("returnMsg", "请选择正确的充值方式");
            return JsonResult.asFalseModel(result);
        }
        // 充值金额四舍五入处理
        BigDecimal rechargeMoney = new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_DOWN);
        // 调用充值方法
        rechargeManager.rechargeAccount(user, rechargeMoney, mode);
        result.put("returnMsg", "充值成功");
        return JsonResult.asTrueModel(result);
    }

    /**
     * 充值流水的信息展示(分页)
     * @author Canon4G
     * @param pageNum 页码
     * @return  JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "getRechargeList", method = RequestMethod.POST)
    public JsonResult getRechargeList(@RequestParam int pageNum) {
        Map<String, Object> result = new HashMap<>();
        RechargeDetail rechargeDetail = new RechargeDetail();
        MyPage<RechargeDetail> myPage = rechargeManager.getRechargeDetailList(rechargeDetail, pageNum, PAGESIZE);
        Set userCodes = new HashSet();
        for (RechargeDetail recharge : myPage.getList()) {
            userCodes.add(recharge.getUserCode());
        }
        Map<String, String> userNames = userManager.getUserNames(userCodes);
        List<RechargeDetail> rechargeDetails = new ArrayList<>();
        for (RechargeDetail recharge : myPage.getList()) {
            recharge.setUserName(userNames.get(recharge.getUserCode()));
            rechargeDetails.add(recharge);
        }
        result.put("list", myPage.getList());
        result.put("count", myPage.getCount());
        result.put("pageNum", pageNum);
        result.put("pageSize", PAGESIZE);
        long pageTotal = (myPage.getCount() + PAGESIZE - 1) / PAGESIZE;
        result.put("pageTotal", String.valueOf(pageTotal));
        return JsonResult.asTrueModel(result);
    }
}
