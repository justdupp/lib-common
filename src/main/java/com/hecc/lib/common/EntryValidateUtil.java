package com.hecc.lib.common;

import com.hecc.lib.exception.HeccException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuhoujun
 * @description: 校验工具类
 * @date: Created In 4:47 PM on 2019/4/10.
 */
public class EntryValidateUtil<T> {

    private static Logger logger = LoggerFactory.getLogger(EntryValidateUtil.class);

    public Class<T> clazz;

    /**
     * 构造函数
     *
     * @param clazz
     */
    public EntryValidateUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 验证枚举类
     *
     * @param t
     * @param signValue
     * @return
     */
    public boolean validate(T t, String signValue) {
        try {
            String waitSign = "";
            Field[] allFields = clazz.getDeclaredFields();
            List<Field> fields = new ArrayList<>();
            for (Field field : allFields) {
                if (field.isAnnotationPresent(EntryValidate.class)) {
                    fields.add(field);
                }
            }
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                field.setAccessible(true);
                EntryValidate entryValidate = field.getAnnotation(EntryValidate.class);
                if (entryValidate.validNull()) {
                    if (field.get(t) == null) {
                        throw new HeccException(1000, "参数不完整");
                    }
                }
                if (i == fields.size() - 1) {
                    waitSign += field.getName() + "=" + field.get(t);
                } else {
                    waitSign += field.getName() + "=" + field.get(t) + "&";
                }
            }
            String sign = MD5Sign.getMD5Sign(waitSign);
            return sign.equals(signValue);
        } catch (Exception e) {
            logger.error("entry validate fail >> e:{}", e);
            throw new HeccException(1000, "加密校验失败");
        }
    }

    static class TestEntry {
        @EntryValidate
        private String name;
        @EntryValidate
        private String idCard;
        @EntryValidate
        private String sex;
        private String aName;
        @EntryValidate
        private String zSex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getaName() {
            return aName;
        }

        public void setaName(String aName) {
            this.aName = aName;
        }

        public String getzSex() {
            return zSex;
        }

        public void setzSex(String zSex) {
            this.zSex = zSex;
        }
    }

    public static void main(String[] args) {
        TestEntry entryParams = new TestEntry();
        entryParams.setIdCard("123456");
        entryParams.setName("徐厚军");
        entryParams.setSex("男");
        entryParams.setaName("hgjbn");
        entryParams.setzSex("iuk");
        EntryValidateUtil<TestEntry> entryValidateUtil = new EntryValidateUtil(TestEntry.class);
        if (entryValidateUtil.validate(entryParams, "844C7999EBC6CE132D44AE32D4DBB0CA")) {
            System.out.println("验证通过");
        }
    }


}
