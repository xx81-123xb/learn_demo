package com.dupenghao.demo;

import java.util.*;

import static java.util.Arrays.asList;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class Max_Min_Count_Demo {
    public static void main(String[] args) {
//        maxStrLength();
//        maxIntegerValue();
        maxSalaryPerson();
    }

    private static void maxSalaryPerson(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        Optional<Person> person = personList.stream().max(Comparator.comparing(Person::getSalary));
        System.out.println(person);

    }

    private static void maxIntegerValue(){
        List<Integer> nums = asList(7, 6, 9, 4, 11, 6);
        Optional<Integer> max = nums.stream().max(Comparator.comparingInt(n -> n));
        System.out.println(max);
    }

    private static void maxStrLength() {
        List<String> strings = asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
//        strings.stream().max((s1,s2)->{
//            return Optional.of();
//        });
        Optional<String> max = strings.stream().max(Comparator.comparingInt(String::length));
        System.out.println(max);
    }
}
