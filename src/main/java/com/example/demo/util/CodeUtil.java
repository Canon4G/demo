package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 编码生成工具类
 * create by Canon4G 2018-12-14
 **/
public class CodeUtil {

    private static final char[] digit = {'0','1','2','3',
            '4','5','6','7','8','9','a','b',
            'c','d','e','f','g','h','i','j',
            'k','l','m','n','o','p','q','r',
            's','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H',
            'I','J','K','L','M','N','O','P',
            'Q','R','S','T','U','V','W','X','Y','Z'};

    public static String getDateUUID(String Pattern) {
        String uuid = UUIDHelper.uuid();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Pattern);
        String date = simpleDateFormat.format(new Date());
        StringBuffer code = new StringBuffer();
        code.append(date).append(uuid);
        return code.toString();
    }

    /**
     * 生成33位时间+UUID字符串
     * @return String
     */
    public static String getDateUUID() {
        String uuid = UUIDHelper.uuid();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = simpleDateFormat.format(new Date());
        return date + uuid;
    }

    /**
     * 生成20位的随机字符串
     * @return String
     */
    public static String generatorRandomCode(){
        Long times = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(times);
        Random r = new Random();
        int length = digit.length;
        for(int i=0;i<7;i++){
            int d = r.nextInt(length);
            char c = digit[d];
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 根据时间生成19位流水号
     * @return String
     */
    public static String SerialNumber() {
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        StringBuilder sb = new StringBuilder();
        sb.append(simpleDateFormat.format(new Date()));
        Integer i = new Random().nextInt(100000);
        for (int j = 0; j < 5 - i.toString().length(); j++) {
            sb.append("0");
        }
        sb.append(i);
        return sb.toString();
    }

    /**
     * 根据day生成19位接入方流水号
     * @return String
     */
    public static String accessSerialNum() {
        String pattern = "dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        StringBuilder sb = new StringBuilder();
        Integer i = new Random().nextInt(10000);
        for (int j = 0; j < 4 - i.toString().length(); j++) {
            sb.append("0");
        }
        sb.append(i).append(simpleDateFormat.format(new Date())).append(System.currentTimeMillis());
        return sb.toString();
    }

    /**
     * 唯一编码生成器
     * @param prefix
     * @return String
     */
    public static String generatorCode(String prefix){
        return prefix + getDateUUID();
    }

    public static void main(String[] args) {
        System.out.println(getDateUUID());
    }
}
