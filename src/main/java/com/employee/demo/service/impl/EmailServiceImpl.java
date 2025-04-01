package com.employee.demo.service.impl;

import com.employee.demo.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;  // ✅ Inject JavaMailSender

    @Override
    public void sendPasswordResetEmail(String toEmail, String employeeName) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom("gulshan01032003yadav@gmail.com");
//        helper.setFrom("rounak2003yadav@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject("Password Reset Confirmation");

        String emailContent = "<p>Hello " + employeeName + ",</p>"
                + "<p>Your password has been successfully changed.</p>"
                + "<p>If you did not request this change, please contact our support team immediately.</p>"
                + "<p>Best Regards,</p><p>Your Company Team</p>";

        helper.setText(emailContent, true); // Enable HTML format
        mailSender.send(mimeMessage);
        System.out.println("Mail sent successfully!!");
    }

}

