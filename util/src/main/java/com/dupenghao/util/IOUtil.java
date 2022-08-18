package com.dupenghao.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 杜鹏豪 on 2022/8/18.
 */
public class IOUtil {

    public static File outputFile(byte[] bytes,String path,String fileName){
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("文件未找到!");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件输出异常!");
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}
