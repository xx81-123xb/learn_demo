package com.dupenghao.reflect.demo;

import com.dupenghao.reflect.pojo.ECBSentence;

/**
 * Created by 杜鹏豪 on 2022/8/22.
 */
public class TestReflect {

    public static void main(String[] args){
        ECBSentence sentence = new ECBSentence("ECB");
        sentence.setId("$ABECB");
        sentence.setData("data");
        sentence.show();
    }

}
