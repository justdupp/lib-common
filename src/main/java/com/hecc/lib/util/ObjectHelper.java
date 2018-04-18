package com.hecc.lib.util;

/**
 * @author xuhoujun
 * @description: 对象工具类
 * @date: Created In 下午8:27 on 2018/4/18.
 */
public class ObjectHelper {

    private ObjectHelper() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * 安全类型转换。
     *
     * @param source 源类型实例。
     * @param target 目标类。
     * @param <T>    目标类型。
     * @return 如成功，则源类型转换为目标类型，否则返回“null”。
     */
    public static <T> T safeCast(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        if (target.isInstance(source)){
            return target.cast(source);
        }
        return null;
    }

}
