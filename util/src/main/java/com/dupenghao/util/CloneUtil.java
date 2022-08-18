package com.dupenghao.util;

import java.io.*;
//import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by 杜鹏豪 on 2022/8/18.
 */
public class CloneUtil {

    public static Object deepClone(Object object){
        Object result = null;
        //1.输出流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            byte[] bytes = outputStream.toByteArray();
//            序列化到文件
            IOUtil.outputFile(bytes,System.getProperty("user.dir")+"/tmp", new Date().getTime()+".properties");
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            result= objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
