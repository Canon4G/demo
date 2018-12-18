package com.example.demo.enums;

/**
 * 充值方式枚举
 * create by Canon4G 2018-12-14
 **/
public enum RechargeMode {

    WEI_CHAT("0", "微信"),
    ALI_PAY("1", "支付宝"),
    BANK("2", "银行卡");


    private String value;
    private String desc;

    RechargeMode(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public RechargeMode setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public RechargeMode setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
