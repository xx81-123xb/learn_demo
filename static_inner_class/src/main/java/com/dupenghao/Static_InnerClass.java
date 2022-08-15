package com.dupenghao;

/**
 * Created by 杜鹏豪 on 2022/8/15.
 */
public class Static_InnerClass {
    public static void main(String[] args) {
        People people = new People();
        People_2 people_2 = new People_2();

    }
    //dph:静态方法中必须用静态对象(针对本类),原因是:Static_InnerClass这个类在加载进内存的时候所有静态代码块和静态成员会
    // 合并为一个代码块然后按顺序执行
    private static class People{

    }
}

class People_2{

}
