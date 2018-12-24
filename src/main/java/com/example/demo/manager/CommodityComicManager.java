package com.example.demo.manager;

import com.example.demo.model.CommodityComic;
import com.example.demo.util.MyPage;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

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

    /**
     * 添加商品信息
     * @author Canon4G
     * @param comicName         漫画名称
     * @param comicPrice        漫画价格
     * @param comicInventory    漫画库存
     * @param comicType         漫画类型
     */
    void addComic(String comicName, String comicPrice, String comicInventory, String comicType);

    /**
     * 修改商品信息
     * @author Canon4G
     * @param comicName         漫画名称
     * @param comicType         漫画类型
     * @param comicPrice        漫画价格
     * @param comicCode         商品编码
     * @param comicInventory    商品库存
     */
    void updateComic(String comicName, String comicType, String comicPrice, String comicCode, String comicInventory);

    /**
     * 删除商品信息
     * @author Canon4G
     * @param comicCode         商品编码
     */
    void deleteComic(String comicCode);

    /**
     * 获得商品名
     * @author Canon4G
     * @param comicCodes 商品编号集合
     * @return map
     */
    Map<String, String> getComicNames(Set<String> comicCodes);
}
