package com.example.demo.manager;

import com.example.demo.model.RechargeDetail;
import com.example.demo.model.User;
import com.example.demo.util.MyPage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * create by Canon4G 2018-12-16
 **/
@Service
public interface RechargeManager {

    /**
     * 用户充值
     * @author Canon4G
     * @param user      用户信息
     * @param money     充值金额
     * @param mode      充值方式
     */
    void rechargeAccount(User user, BigDecimal money, String mode);

    /**
     * 获得充值流水列表(分页)
     * @author Canon4G
     * @param rechargeDetail    充值流水信息
     * @param pageNum           页码
     * @param pageSize          每页的数量
     * @return  MyPage<RechargeDetail>
     */
    MyPage<RechargeDetail> getRechargeDetailList(RechargeDetail rechargeDetail, int pageNum, int pageSize);
}
