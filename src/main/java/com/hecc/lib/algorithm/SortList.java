package com.hecc.lib.algorithm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @author xuhoujun
 * @description: List工具类
 * @date: Created In 下午7:52 on 2018/5/9.
 */
public class SortList<T> {

    private static final String SORT_DESC = "desc";

    /**
     * 根据方法比较list
     *
     * @param list   列表
     * @param method 方法
     * @param sort   排序方式
     */
    public void sort(List<T> list, final String method, final String sort) {
        Collections.sort(list, (a, b) -> {
            int ret = 0;
            try {
                Method m1 = ((T) a).getClass().getMethod(method, null);
                Method m2 = ((T) b).getClass().getMethod(method, null);
                if (sort != null && SORT_DESC.equals(sort)) {
                    ret = m2.invoke((b), null).toString().compareTo(m1.invoke((a), null).toString());
                } else {
                    ret = m1.invoke((a), null).toString().compareTo(m2.invoke((b), null).toString());
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
            return ret;
        });
    }


}
