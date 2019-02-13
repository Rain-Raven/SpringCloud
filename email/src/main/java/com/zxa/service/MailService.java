package com.zxa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void mimeMail(String fromEmail, List<String> toList, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(fromEmail);
            for (String to : toList) {
                messageHelper.addTo(to);
            }
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("邮件发送失败",e);
        }
    }

    public void mimeMail(String fromEmail, String toEmail, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            messageHelper.setFrom(fromEmail);
            messageHelper.addTo(toEmail);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("邮件发送失败",e);
        }
    }
}
