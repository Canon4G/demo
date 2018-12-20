package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.enums.ComicType;
import com.example.demo.manager.CommodityComicManager;
import com.example.demo.manager.ConsumeManager;
import com.example.demo.manager.UserAccountManager;
import com.example.demo.model.CommodityComic;
import com.example.demo.model.User;
import com.example.demo.model.UserAccount;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 商品——漫画
 * create by Canon4G 2018-12-17
 **/
@Controller
@RequestMapping(value = "comic/")
public class ComicController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ComicController.class);

    private static final Integer PAGESIZE = 10;

    @Autowired
    CommodityComicManager commodityComicManager;

    @Autowired
    ConsumeManager consumeManager;

    @Autowired
    UserAccountManager userAccountManager;

    /**
     * 前台漫画信息展示(分页)
     * @author Canon4G
     * @param comicName         漫画名称
     * @param comicType         漫画分类
     * @param pageNum           当前页码
     * @param comicInventory    库存
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "showComicList", method = RequestMethod.POST)
    public JsonResult showComicList(@RequestParam String comicName,
                                    @RequestParam String comicType,
                                    @RequestParam String comicInventory,
                                    @RequestParam int pageNum) {
        Map<String, Object> result = new HashMap<>();
        CommodityComic commodityComic = new CommodityComic.Builder().comicName(comicName).comicType(comicType)
                .comicInventory(comicInventory).build();
        MyPage<CommodityComic> myPage = commodityComicManager.getComicInfoListPage(commodityComic, pageNum, PAGESIZE);
        result.put("list", myPage.getList());
        result.put("count", myPage.getCount());
        result.put("pageNum", pageNum);
        result.put("pageSize", PAGESIZE);
        long pageTotal = (myPage.getCount() + PAGESIZE - 1) / PAGESIZE;
        result.put("pageTotal", String.valueOf(pageTotal));
        return JsonResult.asTrueModel(result);
    }

    /**
     * 漫画详情信息展示
     * @author Canon4G
     * @param comicCode     漫画编码
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "getComicDetails", method = RequestMethod.POST)
    public JsonResult getComicDetails(@RequestParam String comicCode) {
        Map<String, Object> result = new HashMap<>();
        CommodityComic comic = commodityComicManager.getComicInfo(new CommodityComic.Builder().comicCode(comicCode).build());
        result.put("comic", comic);
        return JsonResult.asTrueModel(result);
    }

    /**
     * 漫画购买
     * @author Canon4G
     * @param request       request
     * @param comicCode     漫画编码
     * @param buyComicNum   购买漫画数量
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "buyComic", method = RequestMethod.POST)
    public JsonResult buyComic(HttpServletRequest request, @RequestParam String comicCode, @RequestParam String buyComicNum) {
        Map<String, Object> result = new HashMap<>();
        // 参数校验
        User user = checkUserSession(request);
        if (null == user) {
            return JsonResult.asFalse();
        }
        if (StringUtils.isBlank(comicCode)) {
            result.put("returnMsg", "购买失败");
            return JsonResult.asFalseModel(result);
        }
        CommodityComic comic = commodityComicManager.getComicInfo(new CommodityComic.Builder().comicCode(comicCode).build());
        if (null == comic) {
            result.put("returnMsg", "购买失败");
            return JsonResult.asFalseModel(result);
        }
        if (!StringUtil.isNumeric(buyComicNum)) {
            result.put("returnMsg", "请输入正确的购买数量");
            return JsonResult.asFalseModel(result);
        }
        if (0 >= Integer.parseInt(buyComicNum)) {
            result.put("returnMsg", "请输入正确的购买数量");
            return JsonResult.asFalseModel(result);
        }
        if (Integer.parseInt(comic.getComicInventory()) < Integer.parseInt(buyComicNum)) {
            result.put("returnMsg", "购买数量不能超过库存");
            return JsonResult.asFalseModel(result);
        }
        // 获取总金额
        BigDecimal totalMoney = comic.getComicPrice().multiply(new BigDecimal(buyComicNum));
        // 获得用户的账户
        UserAccount userAccount = userAccountManager.getUserAccountInfo(new UserAccount.Builder().userCode(user.getUserCode()).build());
        // 获得账户余额
        BigDecimal accountMoney = userAccount.getAccountMoney();
        if (0 < totalMoney.compareTo(accountMoney)) {
            result.put("returnMsg", "账户余额不足");
            return JsonResult.asFalseModel(result);
        }
        consumeManager.buyComic(userAccount, totalMoney, comic, buyComicNum);
        result.put("returnMsg", "购买成功");
        return JsonResult.asTrueModel(result);
    }

    /**
     * 添加漫画商品信息
     * @author  1Canon4G
     * @param comicName         漫画名称
     * @param comicPrice        漫画单价
     * @param comicInventory    漫画库存
     * @param comicType         漫画类型
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "addComic", method = RequestMethod.POST)
    public JsonResult addComic(@RequestParam String comicName,
                               @RequestParam String comicPrice,
                               @RequestParam String comicInventory,
                               @RequestParam String comicType) {
        Map<String, Object> result = new HashMap<>();
        CommodityComic comic = commodityComicManager.getComicInfo(new CommodityComic.Builder().comicName(comicName).build());
        // 参数校验
        if (StringUtils.isBlank(comicName)) {
            result.put("returnMsg", "漫画名不能为空!");
            return JsonResult.asFalseModel(result);
        }
        if (null != comic) {
            result.put("returnMsg", "漫画名重复!");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtils.isBlank(comicPrice)) {
            result.put("returnMsg", "漫画单价不能为空!");
            return JsonResult.asFalseModel(result);
        }
        if (!StringUtil.isNumericOrDecimal(comicPrice)) {
            result.put("returnMsg", "漫画单价必须是数字型!");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtils.isBlank(comicType) || "-1".equals(comicType)) {
            result.put("returnMsg", "请选择正确的漫画类型!");
            return JsonResult.asFalseModel(result);
        }
        comicInventory = (StringUtils.isBlank(comicInventory)) ? "0" : comicInventory;
        commodityComicManager.addComic(comicName, comicPrice, comicInventory, comicType);
        result.put("returnMsg", "添加成功!");
        return JsonResult.asTrueModel(result);
    }

    /**
     * 修改漫画商品信息
     * @author Canon4G
     * @param comicName         漫画名称
     * @param comicType         漫画类型
     * @param comicPrice        漫画单价
     * @param comicCode         商品编码
     * @param comicInventory    商品库存
     * @param comicType2        修改类型
     * @return  JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "updateComic", method = RequestMethod.POST)
    public JsonResult updateComic(@RequestParam String comicName,
                                  @RequestParam String comicType,
                                  @RequestParam String comicPrice,
                                  @RequestParam String comicCode,
                                  @RequestParam String comicInventory,
                                  @RequestParam String comicType2) {
        Map<String, Object> result = new HashMap<>();
        // 参数校验
        if (StringUtils.isBlank(comicName)) {
            result.put("returnMsg", "漫画名不能为空!");
            return JsonResult.asFalseModel(result);
        }
        CommodityComic comic = commodityComicManager.getComicInfo(new CommodityComic.Builder().comicCode(comicCode).build());
        if (!comicName.equals(comic.getComicName())) {
            CommodityComic comic2 = commodityComicManager.getComicInfo(new CommodityComic.Builder().comicName(comicName).build());
            if (null != comic2) {
                result.put("returnMsg", "漫画名重复!");
                return JsonResult.asFalseModel(result);
            }
        }
        if (StringUtils.isBlank(comicPrice)) {
            result.put("returnMsg", "漫画单价不能为空!");
            return JsonResult.asFalseModel(result);
        }
        if (!StringUtil.isNumericOrDecimal(comicPrice)) {
            result.put("returnMsg", "漫画单价必须是数字型!");
            return JsonResult.asFalseModel(result);
        }
        if (StringUtils.isBlank(comicCode)) {
            result.put("returnMsg", "修改失败!");
            return JsonResult.asFalseModel(result);
        }
        if (!StringUtil.isNumeric(comicInventory)) {
            result.put("returnMsg", "请输入正确的库存!");
            return JsonResult.asFalseModel(result);
        }
        comicInventory = (StringUtils.isBlank(comicInventory)) ? "0" : comicInventory;
        if (ComicType.JP_COMIC.getDesc().equals(comicType)) {
            comicType = ComicType.JP_COMIC.getValue();
        } else if (ComicType.HK_COMIC.getDesc().equals(comicType)) {
            comicType = ComicType.HK_COMIC.getValue();
        } else if (ComicType.EU_COMIC.getDesc().equals(comicType)) {
            comicType = ComicType.EU_COMIC.getValue();
        }
        comicType = (StringUtils.isBlank(comicType2) || "-1".equals(comicType2) || comicType2.equals(comicType)) ? comicType : comicType2;
        commodityComicManager.updateComic(comicName, comicType, comicPrice, comicCode, comicInventory);
        result.put("returnMsg", "修改成功!");
        return JsonResult.asTrueModel(result);
    }

    /**
     * 删除商品信息
     * @author Canon4G
     * @param comicCode     商品编码
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "deleteComic", method = RequestMethod.POST)
    public JsonResult deleteComic(@RequestParam String comicCode) {
        Map<String, Object> result = new HashMap<>();
        //  参数校验
        if (StringUtils.isBlank(comicCode)) {
            result.put("returnMsg", "删除失败!");
            return JsonResult.asFalseModel(result);
        }
        commodityComicManager.deleteComic(comicCode);
        result.put("returnMsg", "删除成功!");
        return JsonResult.asTrueModel(result);
    }
}
