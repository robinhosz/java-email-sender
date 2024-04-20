package github.io.robinhosz.emailsender.controller;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    static Dotenv dotenv = Dotenv.load();
    public static final String EMAIL = dotenv.get("EMAIL");

    @PostMapping("/contact")
    public String handleContactForm(@RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("subject") String subject,
                                    @RequestParam("message") String message) {
        // Criar uma mensagem de e-mail
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(EMAIL); // Substitua com o seu endereço de e-mail
        mailMessage.setFrom(EMAIL);
        mailMessage.setSubject("Nova mensagem de contato: " + subject);
        mailMessage.setText("Nome: " + name + "\n" +
                "Email: " + email + "\n" +
                "Telefone: " + phone + "\n" +
                "Mensagem: " + message);

        // Enviar e-mail
        mailSender.send(mailMessage);

        // Redirecionar de volta para a página de contato
        return "redirect:http://localhost:5500/tech-blog/tech-contact.html";

    }
}