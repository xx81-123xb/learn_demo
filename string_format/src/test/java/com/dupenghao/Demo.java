package com.dupenghao;


import com.dupenghao.util.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by 杜鹏豪 on 2022/8/25.
 */
@Slf4j
public class Demo {


    @Test
    public void test(){
        String a="abc";
        a.intern();
//        System.out.println(a == "abc");
        log.info("abc");
    }

}
