package com.dupenghao.demo;


import com.dupenghao.util.ShowUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class Skip_Distinct_Limit_Demo {

    public static void main(String[] args) {

        String str = "abcdefg";
        String[] split = str.split("");
        char[] chars = str.toCharArray();
        Person[] persons=new Person[2];
        persons[0]=new Person("张三", 1800, 18, "male", "china");
        persons[1]=new Person("李四", 1800, 19, "male", "china");
//        System.out.println(split.length);
//        ShowUtil.showArray(split);
        ShowUtil.showArray(persons);
    }

    private static void skip_demo(){

    }
}
