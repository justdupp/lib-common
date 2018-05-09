package com.hecc.lib.algorithm;

import java.math.BigDecimal;

/**
 * @author xuhoujun
 * @description: double工具类
 * @date: Created In 下午7:18 on 2018/5/9.
 */
public class DoubleUtil {

    private static final int DEF_DIV_SCALE = 4;

    /**
     * 加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static Double add(Double v1, Double v2) {
        if (v1 == null || v2 == null) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static Double substract(Double v1, Double v2) {
        if (v1 == null || v2 == null) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static Double multiply(Double v1, Double v2) {
        if (v1 == null || v2 == null) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法运算（保留四位小数）
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static Double divide(Double v1, Double v2) {
        return divide(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 除法运算，scale指定精度
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示精确到小数点后几位
     * @return 两个参数的商
     */
    public static Double divide(Double v1, Double v2, int scale) {
        if (v1 == null || v2 == null) {
            return null;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static Double round(Double v, int scale) {
        if (v == null) {
            return null;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
