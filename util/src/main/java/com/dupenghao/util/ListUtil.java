package com.dupenghao.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 杜鹏豪 on 2022/8/31.
 */
public class ListUtil {

    public static <T> List<T> of(T... objects){
        List<T> result = Arrays.stream(objects).collect(Collectors.toList());
        return result;
    }

}
