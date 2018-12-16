package com.example.demo.util;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串处理工具类
 * create by Canon4G 2018-12-16
 **/
public class StringUtil {

    /**
     * 手機號加密如：156****8686
     * @return String
     */
    public static String encryptMobileNum(String num){
        return StringUtils.isBlank(num) ? "" : num.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }

    /**
     * 身份证号脱敏处理
     * @param idCardNum 身份证号
     * @return String
     */
    public static String encryptIdCardNum(String idCardNum){
        return StringUtils.isBlank(idCardNum) ? "" : idCardNum.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1****$2");
    }

    /**
     * 真实姓名脱敏处理
     * @param realName 真实姓名
     * @return String
     */
    public static String encryptRealName(String realName) {
        return StringUtils.isBlank(realName) ? "" : StringUtils.rightPad(StringUtils.left(realName, 1), StringUtils.length(realName), "*");
    }

    /**
     * 格式化银行卡号
     * @param bankNum 银行卡号
     * @return String
     */
    public static String formatBankNum(String bankNum) {
        return StringUtils.isBlank(bankNum) ? "" : bankNum.replaceAll("\\d{4}(?!$)", "$0 ");
    }

    public static boolean checkEmail(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return Pattern.matches(regex, email);
    }

    public static boolean checkMobile(String num) throws PatternSyntaxException {
        String regExp = "^[1][1,2,3,4,5,6,7,8,9][0-9]{9}$";
        return Pattern.matches(regExp, num);
    }

    /**
     * 判断字符串是否是数字字符串
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(StringUtils.isBlank(str)){
            return false;
        }
        int length = str.length();
        for (int i=0;i<length;i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否是浮点数
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为数字，包括整数和小数
     * @param str
     * @return
     */
    public static boolean isNumericOrDecimal(String str) {
        Pattern num = Pattern.compile("^\\d+$|-\\d+$"); // 就是判断是否为整数
        Pattern decimal = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");//判断是否为小数
        // Pattern pattern = Pattern.compile("[0-9]+.?[0-9]+");
        Matcher isNum = num.matcher(str);
        Matcher isDecimal =  decimal.matcher(str);
        if (isNum.matches() || isDecimal.matches()) {
            return true;
        }

        return false;
    }

    /**
     * 转化价格
     * @param price 价格
     * @return String
     */
    private static DecimalFormat PRICEFORMAT = new DecimalFormat("###0");
    public static String getPrice(Double price) {
        if (price == null) {
            return "";
        }
        return PRICEFORMAT.format(price);
    }

    /**
     * 请求招行接口，签名加密
     * @param data 签名字符串
     * @return String
     */
    public static String getSign(String data) {
        String sign = "";
        try {
            // 创建加密对象
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // 传入要加密的字符串,按指定的字符集将字符串转换为字节流
            messageDigest.update(data.getBytes("UTF-8"));
            byte byteBuffer[] = messageDigest.digest();
            // 將 byte数组转换为16进制string
            sign = HexString(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    private static String HexString(byte[] baSrc) {
        if (baSrc == null) {
            return "";
        }
        int nByteNum = baSrc.length;
        StringBuilder sbResult = new StringBuilder(nByteNum * 2);
        for (byte aBaSrc : baSrc) {
            char chHex;
            byte btHigh = (byte) ((aBaSrc & 0xF0) >> 4);
            if (btHigh < 10) {
                chHex = (char) ('0' + btHigh);
            } else {
                chHex = (char) ('A' + (btHigh - 10));
            }
            sbResult.append(chHex);
            byte btLow = (byte) (aBaSrc & 0x0F);
            if (btLow < 10) {
                chHex = (char) ('0' + btLow);
            } else {
                chHex = (char) ('A' + (btLow - 10));
            }
            sbResult.append(chHex);
        }
        return sbResult.toString();
    }
}
