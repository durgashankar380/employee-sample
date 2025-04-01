package com.employee.demo.service;

import jakarta.mail.MessagingException;

public interface EmailService {

     void sendPasswordResetEmail(String toEmail, String employeeName) throws MessagingException;

}
