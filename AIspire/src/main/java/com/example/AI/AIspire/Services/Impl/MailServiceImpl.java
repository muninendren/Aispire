package com.example.AI.AIspire.Services.Impl;

import com.example.AI.AIspire.Services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("SenderMail");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

       try{
           mailSender.send(message);
       } catch (MailException e) {
           System.out.println(e.getMessage());
       }
        System.out.println("Mail Sent Successfully...");
    }
}

