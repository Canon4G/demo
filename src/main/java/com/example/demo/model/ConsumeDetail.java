package com.example.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 消耗流水类
 */
public class ConsumeDetail implements Serializable {

    private static final long serialVersionUID = 8222135682113887112L;

    private Long id;                        // id

    private String consumeCode;             // 消耗流水编码

    private String accountCode;             // 账户编码

    private BigDecimal consumeMoney;        // 消耗金额

    private String productCode;             // 商品编码

    private String productCount;            // 商品数量

    private Date gmtCreate;                 // 创建时间

    private String userCode;                // 用户编码

    public ConsumeDetail() {
    }

    public ConsumeDetail(Long id, String consumeCode, String userCode, String accountCode, BigDecimal consumeMoney, String productCode, String productCount, Date gmtCreate) {
        this.id = id;
        this.consumeCode = consumeCode;
        this.userCode = userCode;
        this.accountCode = accountCode;
        this.consumeMoney = consumeMoney;
        this.productCode = productCode;
        this.productCount = productCount;
        this.gmtCreate = gmtCreate;
    }

    private ConsumeDetail(Builder builder) {
        setId(builder.id);
        setConsumeCode(builder.consumeCode);
        setUserCode(builder.userCode);
        setAccountCode(builder.accountCode);
        setConsumeMoney(builder.consumeMoney);
        setProductCode(builder.productCode);
        setProductCount(builder.productCount);
        setGmtCreate(builder.gmtCreate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsumeCode() {
        return consumeCode;
    }

    public void setConsumeCode(String consumeCode) {
        this.consumeCode = consumeCode;
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

    public BigDecimal getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(BigDecimal consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


    public static final class Builder {
        private Long id;
        private String consumeCode;
        private String userCode;
        private String accountCode;
        private BigDecimal consumeMoney;
        private String productCode;
        private String productCount;
        private Date gmtCreate;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder consumeCode(String val) {
            consumeCode = val;
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

        public Builder consumeMoney(BigDecimal val) {
            consumeMoney = val;
            return this;
        }

        public Builder productCode(String val) {
            productCode = val;
            return this;
        }

        public Builder productCount(String val) {
            productCount = val;
            return this;
        }

        public Builder gmtCreate(Date val) {
            gmtCreate = val;
            return this;
        }

        public ConsumeDetail build() {
            return new ConsumeDetail(this);
        }
    }
}