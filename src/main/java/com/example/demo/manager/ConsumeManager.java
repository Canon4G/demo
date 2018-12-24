package com.example.demo.manager;

import com.example.demo.model.CommodityComic;
import com.example.demo.model.ConsumeDetail;
import com.example.demo.model.UserAccount;
import com.example.demo.util.MyPage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * create by Canon4G 2018-12-16
 **/
@Service
public interface ConsumeManager {

    /**
     * 购买漫画
     * @author Canon4G
     * @param userAccount   用户账户信息
     * @param totalMoney    此次购买总金额
     * @param comic         漫画商品信息
     * @param buyComicNum   购买数量
     */
    void buyComic(UserAccount userAccount, BigDecimal totalMoney, CommodityComic comic, String buyComicNum);

    /**
     * 获得消耗流水列表(分页)
     * @author Canon4G
     * @param consumeDetail     消耗流水信息
     * @param pageNum           页码
     * @param pageSize          每页的数量
     * @return  MyPage<ConsumeDetail>
     */
    MyPage<ConsumeDetail> getConsumeDetailList(ConsumeDetail consumeDetail, int pageNum, int pageSize);
}
