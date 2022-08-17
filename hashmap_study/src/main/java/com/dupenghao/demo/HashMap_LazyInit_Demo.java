package com.dupenghao.demo;

import lombok.Data;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 杜鹏豪 on 2022/8/16.
 */
public class HashMap_LazyInit_Demo {

    private static void HelloWorld(){
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900,18, "male", "New York"));
        personList.add(new Person("Jack", 7000,18, "male", "Washington"));
        personList.add(new Person("Lily", 7800,18, "female", "Washington"));
        personList.add(new Person("Anni", 8200,18, "female", "New York"));
        personList.add(new Person("Owen", 9500,20, "male", "New York"));
        personList.add(new Person("Alisa", 7900,20, "female", "New York"));

        Optional<Person> first = personList.stream().findFirst();
//        System.out.println(first.isPresent());
        Person person = first.get();
//        System.out.println(person);
        Person person1 = personList.stream().findAny().get();
        System.out.println("ABC");

        personList.stream().anyMatch(person2 -> {
            person.getSex().equals("male");

            return false;
        });
        System.out.println(person1);

    }

    private static void m2() {
        Thread thread = new Thread(()->{
            for(int i=0;i<10;i++){
                HelloWorld();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i==7)
                    throw new RuntimeException("ceshi");
            }
        });
        thread.start();
        try {
            for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
                System.out.println(stackTraceElement);
                System.out.println(stackTraceElement.getClassName());
            }
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void m1() {
        Map<String,String> map=new HashMap<>();
        System.out.println();
        System.out.println();
        map.put("key_1","value_1");
        map.put("key_2","value_2");
        map.put("key_3","value_3");
        map.put("key_4","value_4");
        map.put("key_5","value_5");
        map.put("key_6","value_6");
        map.put("key_7","value_7");
        map.put("key_8","value_8");

        System.out.println();
        map.put("key_9","value_9");
        map.put("key_0","value_0");

        Collection<String> values = map.values();
        Object[] array = values.toArray();
        Arrays.sort(array);
//        List<Object> collect = Arrays.stream(array).collect(Collectors.toList());
        System.out.println(array.length);
        Arrays.stream(array).peek(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                String o1 = (String) o;
                System.out.println(o1);
            }
        }).flatMap(new Function<Object, Stream<?>>() {
            @Override
            public Stream<?> apply(Object o) {
                return null;
            }
        }).count();
        System.out.println("==============end==============");
    }

}

@Data
class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    // 构造方法
    public Person(String name, int salary, int age,String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }
    // 省略了get和set，请自行添加


}
