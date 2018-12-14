package com.example.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户账户类
 */
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 3374593635271157474L;

    private Long id;                        // id

    private String accountCode;             // 账户编号

    private String userCode;                // 用户编号

    private BigDecimal accountMoney;        // 账户余额

    private Date gmtCreate;                 // 创建时间

    private Date gmtModified;               // 修改时间

    public UserAccount() {
    }

    public UserAccount(Long id, String accountCode, String userCode, BigDecimal accountMoney, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.accountCode = accountCode;
        this.userCode = userCode;
        this.accountMoney = accountMoney;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    private UserAccount(Builder builder) {
        setId(builder.id);
        setAccountCode(builder.accountCode);
        setUserCode(builder.userCode);
        setAccountMoney(builder.accountMoney);
        setGmtCreate(builder.gmtCreate);
        setGmtModified(builder.gmtModified);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public BigDecimal getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(BigDecimal accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


    public static final class Builder {
        private Long id;
        private String accountCode;
        private String userCode;
        private BigDecimal accountMoney;
        private Date gmtCreate;
        private Date gmtModified;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder accountCode(String val) {
            accountCode = val;
            return this;
        }

        public Builder userCode(String val) {
            userCode = val;
            return this;
        }

        public Builder accountMoney(BigDecimal val) {
            accountMoney = val;
            return this;
        }

        public Builder gmtCreate(Date val) {
            gmtCreate = val;
            return this;
        }

        public Builder gmtModified(Date val) {
            gmtModified = val;
            return this;
        }

        public UserAccount build() {
            return new UserAccount(this);
        }
    }
}