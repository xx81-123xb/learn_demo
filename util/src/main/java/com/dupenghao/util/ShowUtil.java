package com.dupenghao.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class ShowUtil {

    public static void showList(List list){
        StringBuilder sb = new StringBuilder();
        sb.append("list length:").append(" ").append(list.size()).append("\r\n");
        list.stream().forEach(item->sb.append(item).append("\r\n"));
        System.out.println(sb.toString());
    }

    public static <T> void showArray(T[] array){
        StringBuilder sb = new StringBuilder();
        sb.append("length:").append(" ").append(array.length).append("\r\n");
        sb.append("[ ");
        for (T i : array) {
            sb.append(i).append(" , ");
        }
        sb.delete(sb.lastIndexOf(","),sb.length());
        sb.append(" ]");
        System.out.println(sb.toString());
    }

    public static void showMap(Map map){
        StringBuilder sb = new StringBuilder();
        sb.append("key-value length:").append(" "+map.size()).append("\r\n");
        Set entrySet = map.entrySet();
        for (Object o : entrySet) {
            Map.Entry entry = (Map.Entry) o;
            sb.append("key: ").append(entry.getKey()).append("  value: ").append(entry.getValue()).append("\r\n");
        }
        System.out.println(sb.toString());
    }

}
