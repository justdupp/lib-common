package com.hecc.lib.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhoujun
 * @description: Map工具类
 * @date: Created In 3:52 PM on 2019/4/10.
 */
public class MapUtil {


    /**
     * map转对象
     *
     * @param map       map
     * @param beanClass 对象
     * @return 对象
     * @throws Exception
     */
    public static Object mapToObj(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (null == map) {
            return null;
        }

        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    /**
     * 对象转map
     *
     * @param obj 对象
     * @return map
     * @throws Exception
     */
    public static Map<String, Object> objToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
