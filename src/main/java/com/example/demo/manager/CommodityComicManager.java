package com.example.demo.manager;

import com.example.demo.model.CommodityComic;
import com.example.demo.util.MyPage;
import org.springframework.stereotype.Service;

/**
 * create by Canon4G 2018-12-17
 **/
@Service
public interface CommodityComicManager {

    /**
     * 根据条件获得漫画信息
     * @author Canon4G
     * @param commodityComic    漫画信息
     * @return CommodityComic
     */
    CommodityComic getComicInfo(CommodityComic commodityComic);

    /**
     * 前台漫画信息展示(分页)
     * @author Canon4G
     * @param commodityComic    漫画信息
     * @param pageNum           当前页码
     * @param pageSize          总页数
     * @return MyPage<CommodityComic>
     */
    MyPage<CommodityComic> getComicInfoListPage(CommodityComic commodityComic, int pageNum, int pageSize);

}
