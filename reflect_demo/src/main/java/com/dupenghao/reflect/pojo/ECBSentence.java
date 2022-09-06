package com.dupenghao.reflect.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Created by 杜鹏豪 on 2022/8/22.
 */
@AllArgsConstructor
@NoArgsConstructor
public class ECBSentence extends Sentence {
//    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println("ECB Sentence:");
        System.out.println(this);
    }
}
