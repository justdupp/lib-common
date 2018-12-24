package com.hecc.lib.design;

/**
 * @author xuhoujun
 * @description:
 * @date: Created In 3:29 PM on 2018/11/30.
 */
public class MInister {
    public static void main(String[] args) {
        for(int i =0;i<5;i++){
            Emperor emperor = Emperor.getEmper();
            emperor.say();
        }
    }
}
