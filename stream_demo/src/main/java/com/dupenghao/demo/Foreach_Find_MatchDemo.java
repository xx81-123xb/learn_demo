package com.dupenghao.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class Foreach_Find_MatchDemo {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        list.stream()
//                .parallel()
                .filter(num -> num > 6).forEach(System.out::println);
        Optional<Integer> first = list.stream().findFirst();
        System.out.println(first.get());
        boolean flag = list.stream().allMatch(num -> num > 0);
        System.out.println(flag);
        System.out.println("=======================");
        for (int i = 0; i < 10; i++) {
            System.out.println("第"+i+"次:"+list.stream().findAny().get());
        }
    }

}
