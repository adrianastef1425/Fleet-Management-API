package laboratoria.fleet.fleetmanagementapi.services;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    void sendMessageWithAttachment( String to, String subject, String text, byte[] attachment, String attachmentName)
            throws MessagingException, IOException;
}
