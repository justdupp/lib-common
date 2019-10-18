package com.hecc.lib.util;

import org.dozer.DozerBeanMapper;


/**
 * @author xuhoujun
 * @description: 对象映射工具，基于dozer
 * @date: created In 11:26 AM on 2019/10/18.
 */
public class BeanMapper {

    private static DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    /**
     * 对象映射
     *
     * @param source    当前对象
     * @param destClass 目标对象
     * @param <T>
     * @return
     */
    public <T> T mapper(Object source, Class<T> destClass) {
        return dozerBeanMapper.map(source, destClass);
    }

}
