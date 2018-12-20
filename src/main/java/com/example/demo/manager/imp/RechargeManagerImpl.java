package com.example.demo.manager.imp;

import com.example.demo.enums.RechargeMode;
import com.example.demo.manager.RechargeManager;
import com.example.demo.mapper.RechargeDetailMapper;
import com.example.demo.mapper.UserAccountMapper;
import com.example.demo.model.RechargeDetail;
import com.example.demo.model.User;
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
 * 充值
 * create by Canon4G 2018-12-16
 **/
@Service(value = "RechargeManager")
public class RechargeManagerImpl implements RechargeManager {

    private static Logger logger = LoggerFactory.getLogger(RechargeManagerImpl.class);

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    RechargeDetailMapper rechargeDetailMapper;

    /**
     * 充值业务
     * @author Canon4G
     * @param user      用户信息
     * @param money     充值金额
     * @param mode      充值方式
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void rechargeAccount(User user, BigDecimal money, String mode) {
        // 通过用户编码查询用户的账户信息
        UserAccount userAccount = userAccountMapper.getInfo(new UserAccount.Builder()
                .userCode(user.getUserCode()).build());
        // 根据充值方式进行充值
        if (mode.equals(RechargeMode.WEI_CHAT.getValue())) {
            addRechargeAndUpdateAccount(money, mode, userAccount);
        } else if (mode.equals(RechargeMode.ALI_PAY.getValue())) {
            // 生成一条充值流水
            addRechargeAndUpdateAccount(money, mode, userAccount);
        } else if (mode.equals(RechargeMode.BANK.getValue())) {
            // 生成一条充值流水
            addRechargeAndUpdateAccount(money, mode, userAccount);
        }
    }

    /**
     * 生成一条充值流水并且更新账户余额
     * @author Canon4G
     * @param money             充值金额
     * @param mode              充值方式
     * @param userAccount       账户信息
     */
    private void addRechargeAndUpdateAccount(BigDecimal money, String mode, UserAccount userAccount) {
        // 生成充值流水编码
        String rechargeCode = "RECHARGE" + CodeUtil.getDateUUID();
        // 账户编码
        String accountCode = userAccount.getAccountCode();
        // 当前账户余额
        BigDecimal accountMoney = (null == userAccount.getAccountMoney()) ? new BigDecimal("0.00") : userAccount.getAccountMoney();
        // 生成一条充值流水
        rechargeDetailMapper.insertSelective(new RechargeDetail.Builder()
                .userCode(userAccount.getUserCode())
                .accountCode(accountCode)
                .rechargeCode(rechargeCode)
                .rechargeMode(mode)
                .rechargeMoney(money)
                .gmtCreate(new Date())
                .build());
        // 更新用户的账户余额
        userAccount.setAccountMoney(accountMoney.add(money));
        userAccount.setGmtModified(new Date());
        userAccountMapper.updateByPrimaryKeySelective(userAccount);
    }
}
