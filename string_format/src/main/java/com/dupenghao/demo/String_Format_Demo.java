package com.dupenghao.demo;

import java.util.Calendar;
import java.util.Formatter;

/**
 * Created by 杜鹏豪 on 2022/8/16.
 */
public class String_Format_Demo {

    public static void main(String[] args) {
//        System.out.println(String.valueOf('A'));
        String.format("",1);
        Formatter formatter = new Formatter();
        String s = formatter.format("你好啊%04d你好啊%d", 12341,999).toString();
//        System.out.println(s);

        /*==================================2022/8/16====================================*/
        String s1 = formatter.format("%tC", Calendar.getInstance()).toString();
        System.out.println(s1);
    }

}
