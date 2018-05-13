package com.hecc.lib.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xuhoujun
 * @description: 数字工具类
 * @date: Created In 下午8:32 on 2018/4/18.
 */
public class NumberUtil {

    private static final Logger logger = LoggerFactory.getLogger(NumberUtil.class);

    private static final Double DOUBLE_FORMATTER = 0.0d;
    private static final Long LONG_FORMATTER = 0L;
    private static final Integer INT_FORMATTER = 0;

    public NumberUtil() {
    }

    public static Double toDouble(Object obj) {
        if (obj == null) {
            return Double.valueOf(DOUBLE_FORMATTER);
        } else {
            String s = String.valueOf(obj);
            if (StringUtils.isEmpty(s)) {
                return Double.valueOf(DOUBLE_FORMATTER);
            } else {
                try {
                    return Double.valueOf(s);
                } catch (NumberFormatException ex) {
                    logger.error("数字转化错误", ex);
                    return Double.valueOf(DOUBLE_FORMATTER);
                }
            }
        }
    }

    public static Long toLong(Object obj) {
        if (obj == null) {
            return Long.valueOf(LONG_FORMATTER);
        } else {
            String s = String.valueOf(obj);
            if (StringUtils.isEmpty(s)) {
                return Long.valueOf(LONG_FORMATTER);
            } else {
                try {
                    int index = s.indexOf(".");
                    index = index > -1 ? index : s.length();
                    return Long.valueOf(Long.parseLong(s.substring(INT_FORMATTER, index)));
                } catch (NumberFormatException ex) {
                    logger.error("数字转化错误", ex);
                    return Long.valueOf(LONG_FORMATTER);
                }
            }
        }
    }

    public static Integer toInt(Object obj) {
        if (obj == null) {
            return Integer.valueOf(INT_FORMATTER);
        } else {
            String s = String.valueOf(obj);
            if (StringUtils.isEmpty(s)) {
                return Integer.valueOf(INT_FORMATTER);
            } else {
                try {
                    int index = s.indexOf(".");
                    index = index > -1 ? index : s.length();
                    return Integer.valueOf(Integer.parseInt(s.substring(0, index)));
                } catch (NumberFormatException ex) {
                    logger.error("数字转化错误", ex);
                    return Integer.valueOf(INT_FORMATTER);
                }
            }
        }
    }

    public static boolean equals(Long s, Long d) {
        if (s == null) {
            s = Long.valueOf(LONG_FORMATTER);
        }
        if (d == null) {
            d = Long.valueOf(LONG_FORMATTER);
        }
        return s.equals(d);
    }
}
