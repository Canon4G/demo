package com.example.demo.manager.imp;

import com.example.demo.manager.ConsumeManager;
import com.example.demo.mapper.CommodityComicMapper;
import com.example.demo.mapper.ConsumeDetailMapper;
import com.example.demo.mapper.UserAccountMapper;
import com.example.demo.model.CommodityComic;
import com.example.demo.model.ConsumeDetail;
import com.example.demo.model.UserAccount;
import com.example.demo.util.CodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付
 * create by Canon4G 2018-12-16
 **/
@Service(value = "ConsumeManager")
public class ConsumeManagerImpl implements ConsumeManager {

    private static Logger logger = LoggerFactory.getLogger(ConsumeManagerImpl.class);

    @Autowired
    ConsumeDetailMapper consumeDetailMapper;

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    CommodityComicMapper commodityComicMapper;

    /**
     * 购买漫画
     * @author Canon4G
     * @param userAccount   用户账户信息
     * @param totalMoney    此次购买总金额
     * @param comic         漫画商品信息
     * @param buyComicNum   购买数量
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void buyComic(UserAccount userAccount, BigDecimal totalMoney, CommodityComic comic, String buyComicNum) {
        // 生成消耗流水
        consumeDetailMapper.insertSelective(new ConsumeDetail.Builder()
                .consumeCode("CONSUME" + CodeUtil.getDateUUID())
                .accountCode(userAccount.getAccountCode())
                .userCode(userAccount.getUserCode())
                .consumeMoney(totalMoney)
                .productCode(comic.getComicCode())
                .productCount(buyComicNum)
                .gmtCreate(new Date())
                .build());
        // 更新账户信息(减少余额)
        userAccount.setAccountMoney(userAccount.getAccountMoney().subtract(totalMoney));
        userAccount.setGmtModified(new Date());
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
        // 更新商品信息(减少库存)
        comic.setComicInventory(String.valueOf(Integer.parseInt(comic.getComicInventory()) - Integer.parseInt(buyComicNum)));
        comic.setGmtModified(new Date());
        commodityComicMapper.updateByPrimaryKeySelective(comic);
    }
}
