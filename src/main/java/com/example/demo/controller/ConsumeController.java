package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.manager.CommodityComicManager;
import com.example.demo.manager.ConsumeManager;
import com.example.demo.manager.UserManager;
import com.example.demo.model.CommodityComic;
import com.example.demo.model.ConsumeDetail;
import com.example.demo.model.User;
import com.example.demo.util.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 消耗
 * create by Canon4G 2018-12-21
 **/
@Controller
@RequestMapping(value = "consume/")
public class ConsumeController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ConsumeController.class);

    private static final Integer PAGESIZE = 10;

    @Autowired
    ConsumeManager consumeManager;

    @Autowired
    UserManager userManager;

    @Autowired
    CommodityComicManager commodityComicManager;

    /**
     * 消耗流水的信息展示(分页)
     * @author Canon4G
     * @param pageNum 页码
     * @return  JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "getConsumeList", method = RequestMethod.POST)
    public JsonResult getConsumeList(@RequestParam int pageNum) {
        Map<String, Object> result = new HashMap<>();
        ConsumeDetail consumeDetail = new ConsumeDetail();
        MyPage<ConsumeDetail> myPage = consumeManager.getConsumeDetailList(consumeDetail, pageNum, PAGESIZE);
        Set userCodes = new HashSet();
        for (ConsumeDetail consume : myPage.getList()) {
            userCodes.add(consume.getUserCode());
        }
        Set comicCodes = new HashSet();
        for (ConsumeDetail consume : myPage.getList()) {
            comicCodes.add(consume.getProductCode());
        }
        Map<String, String> userNames = userManager.getUserNames(userCodes);
        Map<String, String> comicNames = commodityComicManager.getComicNames(comicCodes);
        List<ConsumeDetail> consumeDetails = new ArrayList<>();
        for (ConsumeDetail consume : myPage.getList()) {
            consume.setUserName(userNames.get(consume.getUserCode()));
            consume.setComicName(comicNames.get(consume.getProductCode()));
            consumeDetails.add(consume);
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
