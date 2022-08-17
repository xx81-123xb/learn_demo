package com.dupenghao.demo.util;

import java.util.List;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class ShowUtil {

    private static void showList(List list){
        StringBuilder sb = new StringBuilder();
        sb.append("list length:").append(" ").append(list.size()).append("\r\n");
        list.stream().forEach(item->sb.append(item).append("\r\n"));
        System.out.println(sb.toString());
    }

}
