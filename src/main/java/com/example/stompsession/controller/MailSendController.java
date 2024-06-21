package com.example.stompsession.controller;

import com.example.stompsession.domain.email.request.EmailRequest;
import com.example.stompsession.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MailSendController {

    private final EmailService emailService;

    public MailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email/send")
    public ResponseEntity<Void> sendEmail(@ModelAttribute EmailRequest emailRequest) {
        try {
            emailService.sendMail(emailRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
