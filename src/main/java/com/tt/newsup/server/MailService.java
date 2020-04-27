package com.tt.newsup.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author ：tt
 * @date ：Created in 2020/4/02 7:21 下午
 * @description：异常监控邮件服务
 * @modified By：
 * @version:
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("182467110@qq.com");//发起者
        mailMessage.setTo(to);//接受者
        //多人mailMessage.setTo("1xx.com","2xx.com","3xx.com");
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            javaMailSender.send(mailMessage);
            System.out.println("发送简单邮件");
        }catch (Exception e){
            System.out.println("发送简单邮件失败");
        }
    }
}
