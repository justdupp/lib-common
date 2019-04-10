package com.hecc.lib.common;

import java.security.MessageDigest;

/**
 * @author xuhoujun
 * @description: MD5加密
 * @date: Created In 4:55 PM on 2019/4/10.
 */
public class MD5Sign {
    /**
     * 获取MD5签名
     *
     * @return md5加密字符串
     */
    public static String getMD5Sign(String ciphertext) {

        System.out.println("getMd5Sign:" + ciphertext);

        if ("".equals(ciphertext) || null == ciphertext || "".equals(ciphertext.trim())) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = ciphertext.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
