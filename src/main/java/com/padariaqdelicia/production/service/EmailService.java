package com.padariaqdelicia.production.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.padariaqdelicia.production.domain.pedido.Pedido;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarConfirmacao(Pedido pedido) {
        String linkConfirmacao =
                "http://localhost:8080/pedidos/confirmar?token=" + pedido.getTokenConfirmacao();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("cliente@email.com"); 
        message.setSubject("Confirme seu pedido 🍞");
        message.setText(
                "Olá " + pedido.getClienteNome() + ",\n\n"
                + "Confirme seu pedido clicando no link abaixo:\n"
                + linkConfirmacao + "\n\n"
                + "Padaria Q Delícia"
        );

        mailSender.send(message);
    }
}
