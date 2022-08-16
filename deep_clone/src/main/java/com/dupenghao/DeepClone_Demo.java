package com.dupenghao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杜鹏豪 on 2022/8/16.
 */
public class DeepClone_Demo {

    /**
     * 总结:
     *      1.类在实现Cloneable接口时,如果当前类内只有基本数据类型,则直接用idea自动生成的即可
     *      2.如果类中还有其他引用类型,则必须手动在重写的clone方法中调用引用对象的clone方法克隆一个然后替换原有的,这样才可以
     *          实现深度克隆
     *      3.同样可以实现深度克隆的是将对象转换为字节流然后再从字节流转化为对象
     *
     * @param args
     * @throws CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        People people = new People("张三", 18, 178);
        Car car = new Car();
        people.setCar(car);
        people.setWomens(new ArrayList<String>());
        People clone_people = people.clone();
//        System.out.println("people:"+people.hashCode()+" car:"+people.getCar().hashCode()+" womens:"+people.getWomens().hashCode());
//        System.out.println("clone_people:"+clone_people.hashCode()+"clone_car:"+clone_people.getCar().hashCode()+" clone_womens:"+clone_people.getWomens().hashCode());
//        System.out.println(people.equals(clone_people));
        people.setAge(60);
        people.getWomens().add("abd");
        System.out.println(people.toString()+" womens:"+people.getWomens().size());
        System.out.println(clone_people.toString()+" clone_womens:"+clone_people.getWomens().size());
    }

}

class Car implements Cloneable {
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class People implements Cloneable {
    private String name;
    private int age;
    private int length;
    private Car car;
    private List womens;

    public List getWomens() {
        return womens;
    }

    public void setWomens(List womens) {
        this.womens = womens;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    People() {

    }

    People(String name, int age, int length) {
        this.name = name;
        this.age = age;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    protected People clone() throws CloneNotSupportedException {
        People people = null;
        people = (People) super.clone();
        people.car = (Car) car.clone();
        ArrayList womens = (ArrayList) this.womens;
        people.setWomens((List) womens.clone());;
        return people;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        People people = (People) o;
//
//        if (age != people.age) return false;
//        if (length != people.length) return false;
//        return name.equals(people.name);
//    }

//    @Override
//    public int hashCode() {
//        int result = name.hashCode();
//        result = 31 * result + age;
//        result = 31 * result + length;
//        return result;
//    }


    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", length=" + length +
                ", car=" + car +
                ", womens=" + womens +
                '}';
    }
}
