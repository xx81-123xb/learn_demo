package com.dupenghao.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class Reduce_Demo {

    public static void main(String[] args) {
        sum_demo();
    }

    private static void sum_demo(){
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        Optional<Integer> result = list.stream().reduce((x, y) -> {
            System.out.println("time");
            return x + y;
        });
        System.out.println(result);
    }

}
