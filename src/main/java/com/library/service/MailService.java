package com.library.service;

import com.library.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(final Mail mail) {
        log.info("Starting email preparation... ");
        try {
            SimpleMailMessage mailMessage = createMail(mail);
            javaMailSender.send(mailMessage);
            log.info("The email to {} has been sent", mail.getMailAddress());
        } catch (MailException exception) {
            log.error("Failed to process email sending: {}", exception.getMessage());
        }
    }

    private SimpleMailMessage createMail(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailAddress());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
}
