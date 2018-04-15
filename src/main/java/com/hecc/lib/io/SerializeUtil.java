package com.hecc.lib.io;

import java.io.*;

/**
 * @author xuhoujun
 * @description: 序列化工具类
 * @date: Created In 下午10:28 on 2018/4/12.
 */
public class SerializeUtil {

    /**
     * 序列化
     *
     * @param obj 对象
     * @return byte数组
     */
    public static byte[] serialize(Object obj) {
        ObjectOutputStream oos;
        ByteArrayOutputStream baos;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static class MyTest implements Serializable {
        private String name;
        private Integer age;
        private String sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

    public static void main(String[] args) {
        MyTest test = new MyTest();
        test.setName("周蕴");
        test.setAge(33);
        test.setSex("女");
        byte[] bs = serialize(test);
        Object obj = unserialize(bs);
        System.out.println("ok");

    }
}
