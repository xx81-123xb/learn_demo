package com.dupenghao.ssh.util;

import com.dupenghao.ssh.pojo.DbHostInfo;
import com.dupenghao.ssh.pojo.HostInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杜鹏豪 on 2022/5/12.
 */

public class DBUtil {

    private static Logger log = LoggerFactory.getLogger(DBUtil.class);

    /**
     * 解析SQL文件为SQL list
     *
     * @param file
     * @return
     */
    public static List<String> parseSqlFile(File file) {
        if (!file.exists())
            throw new RuntimeException("文件不存在");
        if (!file.getName().endsWith(".sql"))
            throw new RuntimeException("文件必须是SQL脚本");

        ArrayList<String> result = new ArrayList<>();
        BufferedReader bufReader = null;
        try {

//            bufReader = new BufferedReader(new FileReader(file));
            bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            StringBuilder sql = new StringBuilder();
            String temp = "";
            while ((temp = bufReader.readLine()) != null) {
                temp.trim();
                if (temp.isEmpty() || temp.startsWith("--"))
                    continue;
                if (temp.contains(";")) {
                    //一个sql语句了
                    sql.append(temp);
                    result.add(sql.toString());
                    sql.delete(0, sql.length());
                } else {
                    //不是一个完成的sql语句
                    sql.append(temp);
                    sql.append("\r\n");
                }
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
     * 创建数据库
     *
     * @param databaseName 数据库名
     * @param host         主机
     * @param username     用户名
     * @param password     密码
     * @return
     */
    public static boolean createDataBase(String databaseName, String host, String username, String password) {
        try {
            Connection conn = getConn(host, username, password, null, null);
            Statement stmt = conn.createStatement();
            String sql1 = "drop database if EXISTS " + databaseName;
            stmt.executeUpdate(sql1);
            String sql2 = "CREATE database " + databaseName;
            stmt.executeUpdate(sql2);
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean createDataBase(String databaseName, Connection conn) {
        try {
            String sql = "CREATE DATABASE if not EXISTS " + databaseName;
            int count = conn.createStatement().executeUpdate(sql);
            return count == 1;
//            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
//            return false;
        }
    }

    /**
     * 获取数据库连接,用于创建数据库后获取连接执行SQL语句
     *
     * @param host         主机
     * @param username     用户名
     * @param password     密码
     * @param databaseName 数据库名
     * @return
     */
    public static Connection getConn(String host, String username, String password, String databaseName, String dBPort) {
        if (dBPort == null)
            dBPort = "3306";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            if (databaseName == null) {
                String url = "jdbc:mysql://" + host + ":" + dBPort + "/" + "?user=" + username + "&password=" + password +
                        "&useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true";
                conn = DriverManager.getConnection(url);
            } else {
                String url = "jdbc:mysql://" + host + ":" + dBPort + "/" + databaseName +
                        "?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true";
                conn = DriverManager.getConnection(url, username, password);
            }
            return conn;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("获取数据库连接失败!", e);
        }
    }

    public static Connection getConn(DbHostInfo hostinfo) {
        return getConn(hostinfo.getIp(), hostinfo.getDbusername(), hostinfo.getDbpassword(), "mysql", hostinfo.getDbport());
    }

    public static Connection getConn(DbHostInfo hostinfo, String databaseName) {
        return getConn(hostinfo.getIp(), hostinfo.getDbusername(), hostinfo.getDbpassword(), databaseName, hostinfo.getDbport());
    }


    /**
     * 执行sql语句
     *
     * @param conn
     * @param sql
     * @return
     */
    public static boolean exeSql(Connection conn, String sql) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql.trim());
            stmt.close();
            return true;
        } catch (Exception e) {
            log.error("执行sql出错,sql:{}", sql);
            return false;
        }

    }

}
