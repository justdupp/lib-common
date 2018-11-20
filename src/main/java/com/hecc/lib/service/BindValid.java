package com.hecc.lib.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuhoujun
 * @description: 绑定校验注解
 * @date: Created In 5:09 PM on 2018/11/8.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindValid {

    /**
     * 是否可以为null
     *
     * @return
     */
    boolean nullable() default false;

    /**
     * 是否可以为空
     *
     * @return
     */
    boolean blankable() default false;

    /**
     * 最大长度
     *
     * @return
     */
    int maxLength() default 0;

    /**
     * 最小长度
     *
     * @return
     */
    int minLength() default 0;

    /**
     * 正则检验
     *
     * @return
     */
    RegexType regexType() default RegexType.NULL;

    /**
     * 自定义检验
     *
     * @return
     */
    String regexExpression() default "";

    /**
     * 字段描述
     *
     * @return
     */
    String desc() default "";
}
