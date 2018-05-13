package com.hecc.lib.common;

/**
 * @author xuhoujun
 * @description: 数学算法工具类
 * @date: Created In 下午6:59 on 2018/5/13.
 */
public class MathUtils {

    /**
     * 递归的方式获取x的n次方值
     *
     * @param x 底数
     * @param n 指数值
     * @return x的n次方
     */
    public static long pow(long x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (isEven(n)) {
            return pow(x * x, n / 2);
        } else {
            return pow(x * x, n / 2) * x;
        }
    }

    /**
     * 判断是否偶数 若 你<=0 返回false
     *
     * @param n 待判断数
     * @return 是否偶数
     */
    public static boolean isEven(int n) {
        if (n <= 0) {
            return false;
        }
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isEven(11));
    }
}
