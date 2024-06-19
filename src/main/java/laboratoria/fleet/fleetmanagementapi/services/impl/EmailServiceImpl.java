package laboratoria.fleet.fleetmanagementapi.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import laboratoria.fleet.fleetmanagementapi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMessageWithAttachment(String toEmail, String subject, String text, byte[] attachment,
                                          String attachmentName) throws MessagingException, IOException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("fleetmanagementapiapplication@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);

        // Convert byte array to InputStream and attach it
        ByteArrayInputStream inputStream = new ByteArrayInputStream(attachment);
        helper.addAttachment(attachmentName, new ByteArrayResource(attachment));

        emailSender.send(message);

    }
}
