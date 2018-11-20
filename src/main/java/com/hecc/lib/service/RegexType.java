package com.hecc.lib.service;

/**
 * @author xuhoujun
 * @description: 正则类型
 * @date: Created In 5:12 PM on 2018/11/8.
 */
public enum RegexType {
    /**
     * 是否为null
     */
    NULL,
    /**
     * 特殊字符
     */
    SPECIALCHARACTER,
    /**
     * 中文字符
     */
    CHINESE,
    /**
     * 邮箱
     */
    EMAIL,
    /**
     * ip地址
     */
    IP,
    /**
     * 数字
     */
    NUMBER,
    /**
     * 手机号码
     */
    MOBILE
}
