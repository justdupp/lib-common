package com.hecc.lib.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuhoujun
 * @description: 对字段进行验证
 * @date: Created In 4:42 PM on 2019/4/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EntryValidate {

    /**
     * 非空验证，默认验证，不需要验证请请填false
     *
     * @return
     */
    abstract boolean validNull() default true;
}
