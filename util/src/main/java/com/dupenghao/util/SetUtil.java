package com.dupenghao.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 杜鹏豪 on 2022/9/9.
 */
public class SetUtil {

    public static <T> Set<T> of(T...objects){
        Set<T> result = new HashSet<T>();
        Arrays.stream(objects).forEach(result::add);
        return result;
    }

}
