package com.git.bookstore.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public static final String EMAIL_FROM="umeshkumarmaurya.98@gmail.com";

    public void sendMail(String toEmail,String subject,String body){
        SimpleMailMessage simpleMailMessage  = new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom(EMAIL_FROM);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setText(body);
        javaMailSender.send(simpleMailMessage);
        System.out.println("Email successfully sent to "+toEmail+" .");

    }
}
