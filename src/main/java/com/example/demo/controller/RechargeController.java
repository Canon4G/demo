package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.manager.RechargeManager;
import com.example.demo.model.User;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 充值
 * create by Canon4G 2018-12-16
 **/
@Controller
@RequestMapping(value = "recharge/")
public class RechargeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RechargeController.class);

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
            return JsonResult.asFalse();
        }
        if (StringUtils.isBlank(money)) {
            result.put("returnMsg", "充值金额不能为空");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtil.isNumericOrDecimal(money)) {
            result.put("returnMsg", "充值金额必须是数字型");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtils.isBlank(mode)) {
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
}
