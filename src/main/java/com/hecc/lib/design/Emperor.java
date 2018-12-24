package com.hecc.lib.design;

/**
 * @author xuhoujun
 * @description:
 * @date: Created In 3:27 PM on 2018/11/30.
 */
public class Emperor {
     public static final Emperor emper = new Emperor();
     private Emperor(){
     }
     public static Emperor getEmper(){
         return emper;
     }
     public void say(){
         System.out.println("hello");
     }
}
