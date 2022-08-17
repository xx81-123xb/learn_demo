package com.dupenghao.demo;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;

/**
 * Created by 杜鹏豪 on 2022/8/17.
 */
public class JavaxMailClient {

    //    public String emailHost="smtp.163.com"; //发送邮件的主机
    public static String emailHost = "localhost"; //发送邮件的主机
    public static String transportType = "smtp";         //邮件发送的协议
    public static String fromUser = "dupenghao";         //发件人名称
    public static String fromEmail = "392973793@qq.com"; //发件人邮箱
    public static String authCode = "";                  //授权码
    public static String toEmail = "392973793@qq.com";   //收件人邮箱
    public static String subject = "测试";               //主题信息

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", transportType);
        props.setProperty("mail.host",emailHost);
        props.setProperty("mail.user",fromUser);
        props.setProperty("mail.from",fromEmail);

        Session session = Session.getInstance(props, null);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);


//        new InternetAddress()
    }
}
