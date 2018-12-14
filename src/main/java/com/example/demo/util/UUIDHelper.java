package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * create by Canon4G 2018-12-14
 **/
public class UUIDHelper {
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static final Map<Character, Integer> digitMap = new HashMap();
    public static final int MAX_RADIX;
    public static final int MIN_RADIX = 2;

    public UUIDHelper() {
    }

    public static String toString(long i, int radix) {
        if (radix < 2 || radix > MAX_RADIX) {
            radix = 10;
        }

        if (radix == 10) {
            return Long.toString(i);
        } else {
//            int size = true;
            int charPos = 64;
            char[] buf = new char[65];
            boolean negative = i < 0L;
            if (!negative) {
                i = -i;
            }

            while(i <= (long)(-radix)) {
                buf[charPos--] = digits[(int)(-(i % (long)radix))];
                i /= (long)radix;
            }

            buf[charPos] = digits[(int)(-i)];
            if (negative) {
                --charPos;
                buf[charPos] = '-';
            }

            return new String(buf, charPos, 65 - charPos);
        }
    }

    static NumberFormatException forInputString(String s) {
        return new NumberFormatException("For input string: \"" + s + "\"");
    }

    public static long toNumber(String s, int radix) {
        if (s == null) {
            throw new NumberFormatException("null");
        } else if (radix < 2) {
            throw new NumberFormatException("radix " + radix + " less than Numbers.MIN_RADIX");
        } else if (radix > MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + " greater than Numbers.MAX_RADIX");
        } else {
            long result = 0L;
            boolean negative = false;
            int i = 0;
            int len = s.length();
            long limit = -9223372036854775807L;
            if (len > 0) {
                char firstChar = s.charAt(0);
                if (firstChar < '0') {
                    if (firstChar == '-') {
                        negative = true;
                        limit = -9223372036854775808L;
                    } else if (firstChar != '+') {
                        throw forInputString(s);
                    }

                    if (len == 1) {
                        throw forInputString(s);
                    }

                    ++i;
                }

                Integer digit;
                for(long multmin = limit / (long)radix; i < len; result -= (long)digit) {
                    digit = (Integer)digitMap.get(s.charAt(i++));
                    if (digit == null) {
                        throw forInputString(s);
                    }

                    if (digit < 0) {
                        throw forInputString(s);
                    }

                    if (result < multmin) {
                        throw forInputString(s);
                    }

                    result *= (long)radix;
                    if (result < limit + (long)digit) {
                        throw forInputString(s);
                    }
                }

                return negative ? result : -result;
            } else {
                throw forInputString(s);
            }
        }
    }

    private static String digits(long val, int digits) {
        long hi = 1L << digits * 4;
        return toString(hi | val & hi - 1L, MAX_RADIX).substring(1);
    }

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
        sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
        sb.append(digits(uuid.getMostSignificantBits(), 4));
        sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
        sb.append(digits(uuid.getLeastSignificantBits(), 12));
        return sb.toString();
    }

    static {
        for(int i = 0; i < digits.length; ++i) {
            digitMap.put(digits[i], i);
        }

        MAX_RADIX = digits.length;
    }
}
