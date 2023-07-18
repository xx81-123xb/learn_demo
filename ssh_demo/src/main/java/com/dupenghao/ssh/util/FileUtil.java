package com.dupenghao.ssh.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by 杜鹏豪 on 2022/5/13.
 */

public class FileUtil {

    private static String newLine_Win = "\r\n";
    private static String newLine_Linux = "\n";

    public static final List<String> EXCLUDE_STR;

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    //排除的字段,不需要前端填的参数
    static {
        EXCLUDE_STR = new ArrayList<>();
        EXCLUDE_STR.add("${mysqlurl}");
        EXCLUDE_STR.add("${username}");
        EXCLUDE_STR.add("${password}");
    }

    /**
     * 复制文件夹或文件,并返回复制的字节数
     * @param source
     * @param destination
     * @return
     * @throws IOException
     */
    public static Long copyDirOrFile(String source, String destination) throws IOException {
        Long size = 0L;
        File src = new File(source);
        File des = new File(destination);
        if (!des.exists())
            des.mkdirs();
        if (src.isFile()) {
            size+=src.length();
            Files.copy(Paths.get(source), Paths.get(destination, src.getName()), StandardCopyOption.REPLACE_EXISTING);
        } else if (src.isDirectory()) {
            File nextDir = new File(des, src.getName());
            if (!nextDir.exists())
                nextDir.mkdirs();
            for (File file : src.listFiles()) {
//                log.info(file.getAbsolutePath());
                size+=copyDirOrFile(file.getAbsolutePath(),nextDir.getPath());
            }
        }
        return size;
    }

    /**
     * 读出输入流中的数据并变为字符串返回
     *
     * @param bufferedReader
     * @return
     */
    public static String readAll(BufferedReader bufferedReader) {
        StringBuilder result = null;
        AtomicBoolean flag = new AtomicBoolean(true);
        String temp = null;
        try {
            while ((temp = bufferedReader.readLine()) != null) {
                if (flag.get()) {
                    result = new StringBuilder();
                    flag.getAndSet(false);
                }
                result.append(temp);
                result.append("\n");
            }
            return result == null ? null : result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将prod文件解析为字符串
     *
     * @param file
     * @return
     */
    public static String parseProdFile2String(File file) {
        if (!file.exists())
            throw new RuntimeException("文件不存在");
        if (!file.getName().endsWith(".yml"))
            throw new RuntimeException("文件必须是yml配置文件");

        String result = "";
        BufferedReader bufReader = null;
        try {
            bufReader = new BufferedReader(new FileReader(file));
            StringBuilder line = new StringBuilder();
            String temp = "";
            while ((temp = bufReader.readLine()) != null) {
                if (temp.isEmpty() || temp.startsWith("#"))
                    continue;
                //在这里对目标字符进行替换
                result += temp + newLine_Linux;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readFile2Str(File file) {
        if (!file.exists())
            throw new RuntimeException("文件不存在");

        String result = "";
        BufferedReader bufReader = null;
        try {
            bufReader = new BufferedReader(new FileReader(file));
            StringBuilder line = new StringBuilder();
            String temp = "";
            while ((temp = bufReader.readLine()) != null) {
                if (temp.isEmpty())
                    continue;
                result += temp + newLine_Linux;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readFile2Str(BufferedReader reader) {
        String result = "";
        BufferedReader bufReader = null;
        try {
            bufReader = reader;
            StringBuilder line = new StringBuilder();
            String temp = "";
            while ((temp = bufReader.readLine()) != null) {
                if (temp.isEmpty())
                    continue;
                result += temp + newLine_Linux;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readFile2Str_Win(File file) {
        if (!file.exists())
            throw new RuntimeException("文件不存在");

        String result = "";
        BufferedReader bufReader = null;
        try {
            bufReader = new BufferedReader(new FileReader(file));
            StringBuilder line = new StringBuilder();
            String temp = "";
            while ((temp = bufReader.readLine()) != null) {
                if (temp.isEmpty())
                    continue;
                result += temp + newLine_Win;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 解析prod文件为List,List中的元素是${}这种的
     *
     * @param file
     * @return
     */
    public static List<String> parseProdFile2List(File file) {
        if (!file.exists())
            throw new RuntimeException("文件不存在");
        if (!file.getName().endsWith(".yml"))
            throw new RuntimeException("文件必须是yml配置文件");

        List<String> result = new ArrayList<>();
        BufferedReader bufReader = null;
        try {
            bufReader = new BufferedReader(new FileReader(file));
            StringBuilder line = new StringBuilder();
            String temp = "";
            while ((temp = bufReader.readLine()) != null) {
                if (temp.isEmpty() || temp.startsWith("#"))
                    continue;

                if (temp.contains("$") && !EXCLUDE_STR.contains(temp.substring(temp.indexOf("$")).trim()))
                    result.add(temp.substring(temp.indexOf("$")));

            }
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 把字符串中的中文替换为英文
     *
     * @param inputString
     * @return
     */
    public static String getPinYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = inputString.trim().toCharArray();
        String output = "";
        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {  //判断字符是否是中文
                    //toHanyuPinyinStringArray 如果传入的不是汉字，就不能转换成拼音，那么直接返回null
                    //由于中文有很多是多音字，所以这些字会有多个String，在这里我们默认的选择第一个作为pinyin
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else {
                    output += Character.toString(input[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
        }
        return output;
    }


    /**
     * 根据实际数据和模板文件,进行替换并生成正确更新网络的脚本
     *
     * @param vmName
     * @param templateIp
     * @param realIp
     * @param templateFileBufferReader
     * @return
     */
    public static File rebuildUpdatenetTemplate(String vmName, String templateIp, String realIp, BufferedReader templateFileBufferReader) {
        //确保虚拟机名字没有中文
        vmName = getPinYin(vmName);
        File resultFile = null;
        BufferedReader bufferedReader = null;
        FileOutputStream fos = null;
        try {
            bufferedReader = templateFileBufferReader;
            String tempDirStr = System.getProperty("user.dir") + "/temp";
            File tempDir = new File(tempDirStr);
            if (!tempDir.exists())
                tempDir.mkdirs();
            resultFile = new File(tempDir, "updatenet.sh");
            fos = new FileOutputStream(resultFile);
            String resultStr = "";
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
//                System.out.println("之前:"+temp);
                if (temp.contains("${vmName}")) {
//                    System.out.println("vmName:"+vmName);
                    temp = temp.replace("${vmName}", vmName);
//                    System.out.println(temp);
                } else if (temp.contains("${realIp}")) {
//                    System.out.println("realIp:"+realIp);
                    temp = temp.replace("${realIp}", realIp);
                    temp = temp.replace("${templateIp}", templateIp);
                }
//                System.out.println("之后:"+temp);
                resultStr += temp + newLine_Linux;
            }
            log.info("重构后的sh文件内容:" + resultStr);
            fos.write(resultStr.getBytes());
            fos.flush();
            return resultFile;
        } catch (Exception e) {
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            }
        }
        return resultFile;
    }
}
