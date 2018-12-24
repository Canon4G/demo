package com.example.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值流水类
 */
public class RechargeDetail implements Serializable {

    private static final long serialVersionUID = 8497048632510587273L;

    private Long id;                        // id

    private String rechargeCode;            // 充值流水编号

    private String accountCode;             // 账户编码

    private BigDecimal rechargeMoney;       // 充值金额

    private String rechargeMode;            // 充值方式：0、微信，1、支付宝，3、银行卡

    private Date gmtCreate;                 // 创建时间

    private String userCode;                // 用户编码

    private String userName;                // 用户名（业务需要）

    public RechargeDetail() {
    }

    public RechargeDetail(Long id, String rechargeCode, String userCode, String accountCode, BigDecimal rechargeMoney, String rechargeMode, Date gmtCreate) {
        this.id = id;
        this.rechargeCode = rechargeCode;
        this.userCode = userCode;
        this.accountCode = accountCode;
        this.rechargeMoney = rechargeMoney;
        this.rechargeMode = rechargeMode;
        this.gmtCreate = gmtCreate;
    }

    private RechargeDetail(Builder builder) {
        setId(builder.id);
        setRechargeCode(builder.rechargeCode);
        setUserCode(builder.userCode);
        setAccountCode(builder.accountCode);
        setRechargeMoney(builder.rechargeMoney);
        setRechargeMode(builder.rechargeMode);
        setGmtCreate(builder.gmtCreate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRechargeCode() {
        return rechargeCode;
    }

    public void setRechargeCode(String rechargeCode) {
        this.rechargeCode = rechargeCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getRechargeMode() {
        return rechargeMode;
    }

    public void setRechargeMode(String rechargeMode) {
        this.rechargeMode = rechargeMode;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getUserName() {
        return userName;
    }

    public RechargeDetail setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public static final class Builder {
        private Long id;
        private String rechargeCode;
        private String userCode;
        private String accountCode;
        private BigDecimal rechargeMoney;
        private String rechargeMode;
        private Date gmtCreate;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder rechargeCode(String val) {
            rechargeCode = val;
            return this;
        }

        public Builder userCode(String val) {
            userCode = val;
            return this;
        }

        public Builder accountCode(String val) {
            accountCode = val;
            return this;
        }

        public Builder rechargeMoney(BigDecimal val) {
            rechargeMoney = val;
            return this;
        }

        public Builder rechargeMode(String val) {
            rechargeMode = val;
            return this;
        }

        public Builder gmtCreate(Date val) {
            gmtCreate = val;
            return this;
        }

        public RechargeDetail build() {
            return new RechargeDetail(this);
        }
    }
}