package com.hecc.lib.service;

import com.hecc.lib.exception.HeccException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * @author xuhoujun
 * @description: 检验工具类
 * @date: Created In 5:20 PM on 2018/11/8.
 */
public class ValidUtil {

    private static BindValid bindValid;

    public static void valid(Object object) {
        Class<? extends Object> clz = object.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            validation(field, object);
            field.setAccessible(false);
        }
    }

    public static void validation(Field field, Object object) {
        String desc;
        Object value = null;
        bindValid = field.getAnnotation(BindValid.class);
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (bindValid == null || value == null || StringUtils.isBlank((String) value) || bindValid.desc() == null) {
            return;
        }
        desc = bindValid.desc().equals("") ? field.getName() : bindValid.desc();
        if (!bindValid.nullable()) {
            if (null == value) {
                throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "参数为传入");
            }
        }

        if (!bindValid.blankable()) {
            if (StringUtils.isBlank(value.toString())) {
                throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "不能为空");
            }
        }
        if (value != null && value.toString() != null && StringUtils.isNotBlank((String) value) && value.toString().length() > bindValid.maxLength() && bindValid.maxLength() != 0) {
            throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "长度不能超过"
                    + bindValid.maxLength());
        }

        if (StringUtils.isNotBlank((String) value) && value.toString().length() < bindValid.minLength() && bindValid.minLength() != 0) {
            throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "长度不能小于"
                    + bindValid.minLength());
        }
        if (bindValid.regexType() != RegexType.NULL) {
            switch (bindValid.regexType()) {
                case NULL:
                    break;
                case SPECIALCHARACTER:
                    if (RegexUtils.hasSpecialChar(value.toString())) {
                        throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "不能含有特殊字符");
                    }
                    break;
                case CHINESE:
                    if (RegexUtils.isChinese2(value.toString())) {
                        throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "不能含有中文字符");
                    }
                    break;
                case EMAIL:
                    if (!RegexUtils.isEmail(value.toString())) {
                        throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "地址格式不正确");
                    }
                    break;
                case IP:
                    if (!RegexUtils.isIp(value.toString())) {
                        throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "地址格式不正确");
                    }
                    break;
                case NUMBER:
                    if (!RegexUtils.isNumber(value.toString())) {
                        throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "不是数字");
                    }
                    break;
                case MOBILE:
                    if (!RegexUtils.isPhoneNumber(value.toString())) {
                        throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "不是手机号码");
                    }
                    break;
                default:
                    break;
            }
        }
        if (!bindValid.regexExpression().equals("")) {
            if (value.toString().matches(bindValid.regexExpression())) {
                throw new HeccException(HeccExceptionConstants.PARAMS_ERROR, desc + "格式不正确");
            }
        }

    }
}
