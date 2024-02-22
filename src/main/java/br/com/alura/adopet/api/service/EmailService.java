package br.com.alura.adopet.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    private final static String from = "adopet@email.com.br";

    public void enviarEmail(String to, String subject, String msg) {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setText(msg);

        emailSender.send(email);
    }
}
