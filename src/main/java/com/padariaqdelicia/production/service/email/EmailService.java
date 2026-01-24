package com.padariaqdelicia.production.service.email;

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

        String email = pedido.getClienteEmail();
        String nome = pedido.getClienteNome();
        String token = pedido.getTokenConfirmacao();
        String linkConfirmacao =
                "http://localhost:8080/pedidos/confirmar?token=" + pedido.getTokenConfirmacao();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(pedido.getClienteEmail());
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