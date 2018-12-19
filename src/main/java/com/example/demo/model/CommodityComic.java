package com.example.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CommodityComic implements Serializable {

    private static final long serialVersionUID = 1263006319094903078L;

    private Long id;                        // id

    private String comicCode;               // 漫画编号

    private String comicName;               // 漫画名称

    private String comicType;               // 漫画分类: 0、港慢，1、日漫，2、欧美漫画

    private BigDecimal comicPrice;          // 漫画单价

    private String comicInventory;          // 漫画库存

    private Date gmtCreate;                 // 创建时间

    private Date gmtModified;               // 修改时间

    public CommodityComic() {
    }

    public CommodityComic(Long id, String comicCode, String comicName, String comicType, BigDecimal comicPrice, String comicInventory, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.comicCode = comicCode;
        this.comicName = comicName;
        this.comicType = comicType;
        this.comicPrice = comicPrice;
        this.comicInventory = comicInventory;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    private CommodityComic(Builder builder) {
        setId(builder.id);
        setComicCode(builder.comicCode);
        setComicName(builder.comicName);
        setComicType(builder.comicType);
        setComicPrice(builder.comicPrice);
        setComicInventory(builder.comicInventory);
        setGmtCreate(builder.gmtCreate);
        setGmtModified(builder.gmtModified);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComicCode() {
        return comicCode;
    }

    public void setComicCode(String comicCode) {
        this.comicCode = comicCode;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getComicType() {
        return comicType;
    }

    public void setComicType(String comicType) {
        this.comicType = comicType;
    }

    public BigDecimal getComicPrice() {
        return comicPrice;
    }

    public void setComicPrice(BigDecimal comicPrice) {
        this.comicPrice = comicPrice;
    }

    public String getComicInventory() {
        return comicInventory;
    }

    public void setComicInventory(String comicInventory) {
        this.comicInventory = comicInventory;
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
        private String comicCode;
        private String comicName;
        private String comicType;
        private BigDecimal comicPrice;
        private String comicInventory;
        private Date gmtCreate;
        private Date gmtModified;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder comicCode(String val) {
            comicCode = val;
            return this;
        }

        public Builder comicName(String val) {
            comicName = val;
            return this;
        }

        public Builder comicType(String val) {
            comicType = val;
            return this;
        }

        public Builder comicPrice(BigDecimal val) {
            comicPrice = val;
            return this;
        }

        public Builder comicInventory(String val) {
            comicInventory = val;
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

        public CommodityComic build() {
            return new CommodityComic(this);
        }
    }
}