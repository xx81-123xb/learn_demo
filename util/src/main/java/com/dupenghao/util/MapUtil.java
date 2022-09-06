package com.dupenghao.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 杜鹏豪 on 2022/9/1.
 */
public class MapUtil {

    public static Map<String,Object> of(String key,Object value){
        HashMap<String, Object> map = new HashMap<>();
        map.put(key,value);
        return map;
    }

}
