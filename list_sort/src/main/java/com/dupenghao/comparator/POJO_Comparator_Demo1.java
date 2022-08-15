package com.dupenghao.comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created by 杜鹏豪 on 2022/8/15.
 * 对象比较器第一次测试
 */
public class POJO_Comparator_Demo1 {


    public static void main(String[] args) {
        Peopel peopel_1 = new Peopel("张三",18,178);
        Peopel peopel_2 = new Peopel("李四",18,180);
        Peopel peopel_3 = new Peopel("王五",19,178);
        Peopel peopel_4 = new Peopel("杜六",20,178);
        Peopel peopel_5 = new Peopel("林七",20,169);
        Peopel peopel_6 = new Peopel("阿八",20,169);
        Peopel peopel_7 = new Peopel("阿八",20,169);

        ArrayList<Peopel> peopels = new ArrayList<>();

        peopels.add(peopel_1);
        peopels.add(peopel_2);
        peopels.add(peopel_3);
        peopels.add(peopel_4);
        peopels.add(peopel_5);
        peopels.add(peopel_6);
        peopels.add(peopel_7);

        /**
         * 规则:
         *  先按照年龄升序排,如果年龄相等则身高高的升序,如果身高还一样则按照名称的字典来排序
         */
        Comparator<Peopel> comparator = new Comparator<Peopel>() {
            @Override
            public int compare(Peopel o1, Peopel o2) {
                //返回整数则是升序,复数则是逆序
                int age_sub = o1.getAge() - o2.getAge();
                int len_sub = o1.getLength() - o2.getLength();
                return age_sub==0?(len_sub==0?(o2.getName().compareTo(o1.getName())):len_sub):age_sub;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };

//        peopels.sort(comparator);
//        peopels.forEach(peopel -> System.out.println(peopel));

        Object[] objects = peopels.toArray();
        Arrays.sort(objects);
        for (Object object : objects) {
            System.out.println(object);
        }

    }

}
@Data
@NoArgsConstructor
class Peopel implements Comparable{

    private static final AtomicInteger COUNT=new AtomicInteger(0);

    private String name;
    private int age;
    private int length;
    private int count;

    public Peopel(String name,int age,int length){
        this.name=name;
        this.age=age;
        this.length=length;
        this.count=COUNT.getAndIncrement();
    }

    @Override
    public String toString() {
        return "Peopel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", length=" + length +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Peopel o1 = this;
        Peopel o2 = (Peopel) o;
        int age_sub = o1.getAge() - o2.getAge();
        int len_sub = o1.getLength() - o2.getLength();
        return age_sub==0?(len_sub==0?(o2.getName().compareTo(o1.getName())):len_sub):age_sub;
    }
}
