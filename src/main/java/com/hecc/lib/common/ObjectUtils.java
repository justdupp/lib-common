package com.hecc.lib.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @author xuhoujun
 * @description: 对象工具类
 * @date: Created In 下午8:17 on 2018/2/28.
 */
public class ObjectUtils {

    /**
     * 比较两个bean对象的字段名称和值是否相等
     *
     * @param source 目标对象
     * @param target 源对象
     * @return
     */
    public static boolean fieldNameAndValueEquals(Object source, Object target) {
        if (source == null || target == null) {
            return false;
        }
        boolean result = true;
        result = resultOfEquals(source, target, result);
        System.out.println("对比结果为 " + result);
        return result;
    }

    /**
     * 获取对比结果
     *
     * @param source 源目标
     * @param target 对比目标
     * @param result 对比结果
     * @return
     */
    private static boolean resultOfEquals(Object source, Object target, boolean result) {
        Class<?> sourceClass = source.getClass();
        Field[] fields = sourceClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String sourceValue = getClassValue(source, fieldName) == null ? "" : getClassValue(source, fieldName)
                    .toString();
            String targetValue = getClassValue(target, fieldName) == null ? "" : getClassValue(target, fieldName)
                    .toString();
            if (!sourceValue.equals(targetValue)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 根据字段名称取值
     *
     * @param obj       对象
     * @param fieldName 字段
     * @return object
     */
    public static Object getClassValue(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        }
        try {
            Class clz = obj.getClass();
            Method[] methods = clz.getMethods();
            for (int i = 0; i < methods.length; i++) {
                /**
                 * 获取以get开头的方法
                 */
                if (!methods[i].getName().startsWith("get")) {
                    continue;
                }
                Object objValue;
                try {
                    objValue = methods[i].invoke(obj, new Object[]{});
                } catch (Exception e) {
                    System.out.println("反射取值出错：" + e);
                    continue;
                }
                if (objValue == null) {
                    continue;
                }
                if (methods[i].getName().toUpperCase().equals(fieldName.toUpperCase())
                        || methods[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                    return objValue;
                }
            }
        } catch (Exception e) {
            System.out.println("取方法出错！" + e);
        }
        return null;
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
        if (target.isInstance(source)) {
            return target.cast(source);
        }
        return null;
    }

}
