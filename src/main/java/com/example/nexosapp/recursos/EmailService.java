package com.example.nexosapp.recursos;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Método para enviar un correo
     * @param from
     * @param to
     * @param subject
     * @param text
     */
    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    /**
     * Método para enviar un correo con un archivo adjunto
     * @param from
     * @param to
     * @param subject
     * @param text
     * @param attachName
     * @param inputStream
     * @throws MessagingException
     */
    public void sendWithAttach(String from, String to, String subject,
                               String text, String attachName,
                               InputStreamSource inputStream) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.addAttachment(attachName, inputStream);
        mailSender.send(message);
    }

    /**
     * Método para enviar un correo con contenido HTML
     * @param from
     * @param to
     * @param subject
     * @param htmlContent
     * @throws MessagingException
     */
    public void sendWithHtml(String from, String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }

}