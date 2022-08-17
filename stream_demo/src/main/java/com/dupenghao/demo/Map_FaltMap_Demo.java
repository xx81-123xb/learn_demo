package com.dupenghao.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.dupenghao.util.ShowUtil.showList;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class Map_FaltMap_Demo {

    public static void main(String[] args) {
//        map_demo1();
//        map_demo2();
        flatMap_demo1();
    }

    private static void flatMap_demo1() {
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> collect = list.stream().flatMap(s -> {
            String[] split_s = s.split(",");
            return Arrays.stream(split_s);
        }).collect(Collectors.toList());
        showList(collect);
    }

    private static void map_demo2() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        List<Person> collect = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        showList(collect);
    }

    private static void map_demo1() {
        String[] strings = {"abcd", "bcdd", "defde", "fTr"};
        List<Integer> nums = Arrays.asList(1, 3, 5, 7, 9, 11);
        Arrays.stream(strings).map(String::toUpperCase).collect(Collectors.toList());
    }
}
