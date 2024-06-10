package com.quocdat.java5.service.serviceimpl;

import com.quocdat.java5.model.MailModel;
import com.quocdat.java5.service.MailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendMail(MailModel mailModel) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setTo(mailModel.getTo());
        helper.setFrom(mailModel.getFrom());
        helper.setSubject(mailModel.getSubject());
        helper.setText(mailModel.getBody());
        helper.setReplyTo(mailModel.getFrom());
        mailSender.send(message);
    }
}
