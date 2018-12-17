package com.example.demo.enums;

/**
 * 漫画类型枚举类
 * create by Canon4G 2018-12-17
 **/
public enum  ComicType {

    HK_COMIC("0", "港漫"),
    JP_COMIC("1", "日漫"),
    EU_COMIC("2", "欧美漫画")
    ;

    private String value;
    private String desc;

    ComicType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public ComicType setValue(String value) {
        this.value = value;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ComicType setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
