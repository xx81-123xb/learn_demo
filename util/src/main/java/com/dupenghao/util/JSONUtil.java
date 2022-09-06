package com.dupenghao.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by 杜鹏豪 on 2022/8/31.
 */
public class JSONUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static synchronized String toJsonString(Object o){
        String string = null;
        try {
            string = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return string;
    }

    public static synchronized String toJsonString(Object o, Charset charset){
        if(charset==null){
            charset= StandardCharsets.UTF_8;
        }
        String string = null;
        try {
            string = new String(objectMapper.writeValueAsBytes(o),charset);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return string;
    }

}
