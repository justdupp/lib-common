package com.hecc.lib.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @Auther xuhoujun
 * @Description:   对象工具类
 * @Date: Created In 下午8:17 on 2018/2/28.
 */
public class ObjectUtils {

    /**
     * 比较两个bean对象的字段名称和值是否相等
     * @param source
     * @param target
     * @return
     */
    public static boolean fieldNameAndValueEquals(Object source, Object target) {
        if (source == null || target == null) {
            return false;
        }
        boolean rv = true;
        rv = resultOfEquals(source, target, rv);
        System.out.println("对比结果为 " + rv);
        return rv;
    }

    /**
     * 获取对比结果
     * @param source   源目标
     * @param target   对比目标
     * @param rv  对比结果
     * @return
     */
    private static boolean resultOfEquals(Object source, Object target, boolean rv) {
        Class<?> sourceClass = source.getClass();
        Field[] fields = sourceClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            String sourceValue = getClassValue(source, fieldName) == null ? "" : getClassValue(source, fieldName)
                    .toString();
            String targetValue = getClassValue(target, fieldName) == null ? "" : getClassValue(target, fieldName)
                    .toString();
            if (!sourceValue.equals(targetValue)) {
                rv = false;
                break;
            }
        }
        return rv;
    }

    /**
     * 根据字段名称取值
     * @param obj          对象
     * @param fieldName    字段
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
                Object objValue ;
                try {
                    objValue = methods[i].invoke(obj, new Object[] {});
                } catch (Exception e) {
                    System.out.println("反射取值出错：" + e.toString());
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
}
