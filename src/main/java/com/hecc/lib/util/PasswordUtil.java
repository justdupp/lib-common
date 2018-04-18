package com.hecc.lib.util;

/**
 * @author xuhoujun
 * @description: 密码工具类
 * @date: Created In 下午8:34 on 2018/4/18.
 */
public class PasswordUtil {

    private final static int RANDOM_NUM = 1000;

    private PasswordUtil() {
    }

    public static String generate(int pwdLength) {
        String[] pwdStr = {"qwertyuiopasdfghjklzxcvbnm", "QWERTYUIOPASDFGHJKLZXCVBNM",
                "0123456789", "~!@#$%^&*.?-_+="};

        String pwd;
        char[] chs = new char[pwdLength];
        /**
         * 保证密码包含四组字符
         */
        for (int i = 0; i < pwdStr.length; i++) {
            int idx = (int) (Math.random() * pwdStr[i].length());
            chs[i] = pwdStr[i].charAt(idx);
        }
        /**
         * 保证密码的长度
         */
        for (int i = pwdStr.length; i < pwdLength; i++) {
            int arrIdx = (int) (Math.random() * pwdStr.length);
            int strIdx = (int) (Math.random() * pwdStr[arrIdx].length());
            chs[i] = pwdStr[arrIdx].charAt(strIdx);
        }
        /**
         * 随机顺序
         */
        for (int i = 0; i < RANDOM_NUM; i++) {
            int idx1 = (int) (Math.random() * chs.length);
            int idx2 = (int) (Math.random() * chs.length);
            if (idx1 == idx2) {
                continue;
            }
            char tempChar = chs[idx1];
            chs[idx1] = chs[idx2];
            chs[idx2] = tempChar;
        }
        pwd = new String(chs);
        return pwd;
    }
}
