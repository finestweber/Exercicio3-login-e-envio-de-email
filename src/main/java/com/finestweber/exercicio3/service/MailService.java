package com.finestweber.exercicio3.service;

import com.finestweber.exercicio3.entety.Usuario;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${verification.url}")
    private String verifyURL;

    public void sendVerificationEmail(Usuario usuario) throws MessagingException, IOException {
        String toAddress = usuario.getEmail();
        String senderName = "Finest Weber";
        String fromAddress = "finestweber@gmail.com";
        String subject = "Verifique sua Autenticação";

        String htmlContent = readHtmlContent("index.html", usuario);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

    private String readHtmlContent(String fileName, Usuario usuario) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] byteArray = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String htmlContent = new String(byteArray, StandardCharsets.UTF_8);

        String verifyUrl = verifyURL + usuario.getVerificationCode();
        htmlContent = htmlContent.replace("[[NAME]]", usuario.getName());
        htmlContent = htmlContent.replace("[[URL]]", verifyUrl);

        return htmlContent;
    }
}

