package com.dupenghao.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dupenghao.util.ShowUtil.showMap;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class PartitioningBy_Demo {

    public static void main(String[] args) {
        partition_demo();
    }

    private static void partition_demo(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));

        //dph:将员工按薪资分组:8000是分界线
        Map<Boolean, List<Person>> collectMap = personList.stream().collect(Collectors.partitioningBy(people -> people.getSalary() > 8000));
        showMap(collectMap);
    }

}
