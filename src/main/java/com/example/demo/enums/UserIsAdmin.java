package com.example.demo.enums;

/**
 * 用户权限枚举
 * create by Canon4G 2018-12-19
 **/
public enum UserIsAdmin {
    SUPER_ADMIN("0", "超级管理员"),
    ADMIN("1", "管理员"),
    USER("2", "普通用户")
    ;


    private String value;
    private String desc;

    UserIsAdmin(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public UserIsAdmin setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public UserIsAdmin setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
