package com.dupenghao.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class Filter_Demo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        List<Integer> collect = list.stream().filter(num -> num > 6).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
}
