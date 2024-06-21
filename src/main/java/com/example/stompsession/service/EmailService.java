package com.example.stompsession.service;

import com.example.stompsession.domain.email.EmailMessage;
import com.example.stompsession.domain.email.request.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendMail(EmailRequest emailRequest) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            EmailMessage emailMessage = EmailMessage.builder()
                .to(emailRequest.getEmail())
                .subject("[INTRO] 서비스 소개")
                .message(getEmailContent(emailRequest))
                .build();

            helper.setFrom(fromEmail);
            helper.setTo(emailMessage.getTo());
            helper.setSubject(emailMessage.getSubject());
            helper.setText(emailMessage.getMessage(), true);

            //log.info("Mail Sender Success");

            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getEmailContent(EmailRequest emailRequest) {
        Map<String, Object> model = new HashMap<>();
        model.put("userEmail", emailRequest.getEmail());

        String templateName = "email/intro-service-mail";
        Context context = new Context();
        context.setVariables(model);

        return springTemplateEngine.process(templateName, context);
    }

}
