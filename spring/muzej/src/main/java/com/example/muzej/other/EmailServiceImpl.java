package com.example.muzej.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailServiceImpl {

    @Autowired
    JavaMailSender javaMailSender;
    final String file="static"+ File.separator+"karta.pdf";

    public void sendMail(String sendTo){
        try{
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(sendTo);
            helper.setSubject("Virtuelna posjeta muzeja");
            helper.setText("<h1>Vaša karta je u pdf-u!</h1>", true);
            helper.addAttachment("karta.pdf", new ClassPathResource(file));
            helper.setFrom("ruzicaa997@gmail.com");
            javaMailSender.send(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMailPodsjetnik(String sendTo, String tekst, Integer brojKarte){
        try{
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(sendTo);
            helper.setSubject("Virtuelna posjeta muzeja");
            helper.setText("<h1>"+tekst+"Broj karte: "+brojKarte+ "</h1>", true);
            helper.setFrom("ruzicaa997@gmail.com");
            javaMailSender.send(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
