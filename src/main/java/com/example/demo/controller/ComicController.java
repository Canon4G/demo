package com.example.demo.controller;

import com.example.demo.entity.JsonResult;
import com.example.demo.manager.CommodityComicManager;
import com.example.demo.model.CommodityComic;
import com.example.demo.util.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品——漫画
 * create by Canon4G 2018-12-17
 **/
@Controller
@RequestMapping(value = "comic/")
public class ComicController {

    private static Logger logger = LoggerFactory.getLogger(ComicController.class);

    private static final Integer PAGESIZE = 10;

    @Autowired
    CommodityComicManager commodityComicManager;

    /**
     * 前台漫画信息展示(分页)
     * @author Canon4G
     * @param comicName         漫画名称
     * @param comicType         漫画分类
     * @param pageNum           当前页码
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "showComicList", method = RequestMethod.POST)
    public JsonResult showComicList(@RequestParam String comicName,
                                    @RequestParam String comicType,
                                    @RequestParam int pageNum) {
        Map<String, Object> result = new HashMap<>();
        CommodityComic commodityComic = new CommodityComic.Builder().comicName(comicName).comicType(comicType).build();
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
     * @author
     * @param request       request
     * @param comicCode     漫画编码
     * @param buyComicNum   购买漫画数量
     * @return JsonResult
     */
    @ResponseBody
    @RequestMapping(value = "buyComic", method = RequestMethod.POST)
    public JsonResult buyComic(HttpServletRequest request, @RequestParam String comicCode, @RequestParam String buyComicNum) {
        Map<String, Object> result = new HashMap<>();

        // TODO: 购买漫画的业务逻辑

        return null;
    }
}
