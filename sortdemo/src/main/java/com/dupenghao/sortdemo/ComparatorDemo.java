package com.dupenghao.sortdemo;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by 杜鹏豪 on 2023/8/16.
 */
public class ComparatorDemo {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("def");
        list.add("mno");
        list.add("ghi");
        list.add("jkl");
        list.add("abc");
        list.add("pqr");

        list.sort((o1, o2) -> {
            System.out.println(o1 + " - " + o2);
            return o1.compareTo(o2);
        });

        list.forEach(System.out::println);

    }

}
