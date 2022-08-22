package com.dupenghao.reflect.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * Created by 杜鹏豪 on 2022/8/22.
 */
public abstract class Sentence {

    private String id;
    private String data;

    public abstract void show();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Class<? extends Sentence> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Class<?> superclass = clazz.getSuperclass();
        Field[] superFields = superclass.getDeclaredFields();
        Object temp;
        for (Field superField : superFields) {
            try {
                superField.setAccessible(true);
//                String name = field.getName();
//                System.out.println("field: "+name);
                temp= superField.get(this);
                sb.append(temp).append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for (Field field : fields) {
            try {
                field.setAccessible(true);
//                String name = field.getName();
//                System.out.println("field: "+name);
                temp= field.get(this);
                 sb.append(temp).append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.delete(sb.lastIndexOf(","),sb.length());
        return sb.toString();
    }
}
