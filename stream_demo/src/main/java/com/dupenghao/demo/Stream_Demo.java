package com.dupenghao.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

/**
 * Created by 杜鹏豪 on 2022/8/16.
 */
public class Stream_Demo {

    public static void main(String[] args) {
        System.out.println();
        m1();
    }

    private static void m1() {
        List<Integer> list = Arrays.asList(6, 7, 8, 1, 0, 55, 9, 11, 16, 18);
        list.stream().filter(num -> num > 10).forEach(System.out::println);
//        Comparator.comparingInt(value -> 0);
    }

}
