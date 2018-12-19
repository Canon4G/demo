package com.example.demo.manager.imp;

import com.example.demo.manager.CommodityComicManager;
import com.example.demo.mapper.CommodityComicMapper;
import com.example.demo.model.CommodityComic;
import com.example.demo.util.MyPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品漫画
 * create by Canon4G 2018-12-17
 **/
@Service(value = "CommodityComicManager")
public class CommodityComicManagerImpl implements CommodityComicManager {

    private static Logger logger = LoggerFactory.getLogger(CommodityComicManagerImpl.class);

    @Autowired
    CommodityComicMapper commodityComicMapper;

    /**
     * 根据条件获得漫画信息
     * @author Canon4G
     * @param commodityComic    漫画信息
     * @return CommodityComic
     */
    @Override
    public CommodityComic getComicInfo(CommodityComic commodityComic) {
        return commodityComicMapper.getInfo(commodityComic);
    }

    /**
     * 前台漫画信息展示(分页)
     * @author Canon4G
     * @param commodityComic    漫画信息
     * @param pageNum           当前页码
     * @param pageSize          总页数
     * @return MyPage<CommodityComic>
     */
    @Override
    public MyPage<CommodityComic> getComicInfoListPage(CommodityComic commodityComic, int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<CommodityComic> comics = commodityComicMapper.getInfoList(commodityComic);
        MyPage<CommodityComic> myPage = new MyPage<>();
        myPage.setList(comics);
        myPage.setCount(page.getTotal());
        return myPage;
    }

    /**
     * 添加商品信息
     * @author Canon4G
     * @param comicName         漫画名称
     * @param comicPrice        漫画价格
     * @param comicInventory    漫画库存
     * @param comicType         漫画类型
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addComic(String comicName, String comicPrice, String comicInventory, String comicType) {
        // TODO: 添加商品信息
    }

    /**
     * 修改商品信息
     * @author Canon4G
     * @param comicName         漫画名称
     * @param comicType         漫画类型
     * @param comicPrice        漫画价格
     * @param comicCode         商品编码
     * @param comicInventory    商品库存
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateComic(String comicName, String comicType, String comicPrice, String comicCode, String comicInventory) {
        // TODO: 添加商品信息
    }

    /**
     * 删除商品信息
     * @author Canon4G
     * @param comicCode         商品编码
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteComic(String comicCode) {
        commodityComicMapper.deleteByComicCode(comicCode);
    }
}
