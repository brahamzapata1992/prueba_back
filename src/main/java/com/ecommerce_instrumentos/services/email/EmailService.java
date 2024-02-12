package com.ecommerce_instrumentos.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegistrationSuccessEmail(String to, String userName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Registro Exitoso");
        message.setText("¡Hola " + userName + "! Gracias por registrarte en nuestro sitio de comercio electrónico.");

        javaMailSender.send(message);
    }
}


