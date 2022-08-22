package com.dupenghao.charset_demo;

import com.dupenghao.util.ShowUtil;

import java.nio.charset.Charset;

/**
 * Created by 杜鹏豪 on 2022/8/22.
 */
public class Demo_1 {

    public static void main(String[] args) {
        m1();
//        m2();
    }

    private static void m1(){
        char A='A';
        char a='a';
        char one='0';
        Character[] chars={
                'A','a','0'
        };
//        ShowUtil.showArray(chars);
        for (Character aChar : chars) {
            System.out.println((int) aChar);
        }
        System.out.println(0b1111);
    }

    private static void m2(){
        String str = "0Aa";
        String charset_name = Charset.defaultCharset().displayName();
        System.out.println(charset_name);
        String str_2 = new String(new byte[]{65, 97, 48}, Charset.forName("utf-8"));
        System.out.println(str_2);
    }

}
