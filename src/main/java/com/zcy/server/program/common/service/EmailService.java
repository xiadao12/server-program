package com.zcy.server.program.common.service;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author yangzhouchuan
 * @version 1.0
 * @date 2019-09-27 15:58
 */
@Service
public class EmailService {

    public void sendEmail(String title, String content) {

        System.out.println("发送邮件："  + content);

        String fromMail = "851883560@qq.com";
        String code = "oscvbkleassnbddh";

        String targetMail = "xiadao12@yeah.net";

        Transport transport = null;
        try {
            Properties properties = new Properties();
            // 连接协议
            properties.put("mail.transport.protocol", "smtp");
            // 主机名
            properties.put("mail.smtp.host", "smtp.qq.com");
            // 端口号
            properties.put("mail.smtp.port", 465);
            properties.put("mail.smtp.auth", "true");
            // 设置是否使用ssl安全连接 ---一般都使用
            properties.put("mail.smtp.ssl.enable", "true");
            // 设置是否显示debug信息 true 会在控制台显示相关信息
            properties.put("mail.debug", "false");
            // 得到回话对象
            Session session = Session.getInstance(properties);
            // 获取邮件对象
            Message message = new MimeMessage(session);
            // 设置发件人邮箱地址
            message.setFrom(new InternetAddress(fromMail));
            // 设置收件人邮箱地址
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(targetMail), new InternetAddress(targetMail), new InternetAddress(targetMail)});
            //message.setRecipient(Message.RecipientType.TO, new InternetAddress("xxx@qq.com"));//一个收件人
            // 设置邮件标题
            message.setSubject(title);
            // 设置邮件内容
            message.setText(content);
            // 得到邮差对象
            transport = session.getTransport();
            // 连接自己的邮箱账户
            // 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            transport.connect(fromMail, code);
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
