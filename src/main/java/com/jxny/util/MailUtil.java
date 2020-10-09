package com.jxny.util;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import java.util.Random;

public class MailUtil {
    private static final String HOST_EMAIL ="2529662127@qq.com"; // 发送邮箱
    private static final String AUTHORIZATION_CODE = "ovjuvqykqjdzeahb"; // 授权码
    private static final String HOST ="smtp.qq.com";  // 指定发送的主机 qq
//    public String code;
    public String sendMail(String mailaddr){
//        boolean b =false;
        // 创建连接对象 javax.mail.session
        String code="";
        Properties properties =System.getProperties();

        properties.setProperty("mail.smtp.host", HOST);// 设置邮件服务器

        properties.setProperty("mail.smtp.auth", "true");// 打开认证



        try {

            //QQ邮箱需要下面这段代码，163邮箱不需要

            MailSSLSocketFactory sf = new MailSSLSocketFactory();

            sf.setTrustAllHosts(true);

            properties.put("mail.smtp.ssl.enable", "true");

            properties.put("mail.smtp.ssl.socketFactory", sf);





            // 1.获取默认session对象

            Session session = Session.getDefaultInstance(properties, new Authenticator() {

                public PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(HOST_EMAIL, AUTHORIZATION_CODE); // 发件人邮箱账号、授权码

                }

            });



            // 2.创建邮件对象

            Message message = new MimeMessage(session);

            // 2.1设置发件人

            message.setFrom(new InternetAddress(HOST_EMAIL));

            // 2.2设置接收人

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailaddr));

            // 2.3设置邮件主题

            message.setSubject("验证码");

            //接收生成的验证码
          code= random();
//            code=random();

           // 将验证码存入session
//            session1.setAttribute("code",code);

            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>验证码为："+code+"</h1></body></html>";

            message.setContent(content, "text/html;charset=UTF-8");

            // 3.发送邮件

            Transport.send(message);

//            System.out.println("邮件成功发送!");

        } catch (Exception e) {
          code="false";
            e.printStackTrace();

        }


        return code;
    }
    public String random(){  //随机生成6位验证码
        Random random = new Random();
        String num="";
        for(int i=0;i<6;i++) {
            if (i == 0) {
                num = num + String.valueOf(random.nextInt(9) + 1);
            }
            else {
                num = num + String.valueOf(random.nextInt(10));
            }
        }
//        String  num=String.valueOf(random.nextInt(10));
        return num;
    }
//
//    public static void main(String[] args) {
//        for(int i=0;i<5;i++)
//        System.out.println(new MailUtil().random());
//    }
}
