package JulianoEliandra.TravelAi.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmail(String destinatario, String mensagem, String caminhoAnexo){
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // true para multipart
                helper.setFrom(remetente); // Seu email
                helper.setTo(destinatario);
                helper.setSubject("Roteiro de viagens!");
                helper.setText(mensagem);

                File anexo = new File(caminhoAnexo);
                helper.addAttachment(anexo.getName(), anexo);

                javaMailSender.send(mimeMessage);
                return "Email enviado com sucesso";
            } catch (MessagingException e){
                e.printStackTrace();
                return "Erro ao enviar email: " + e.getMessage();
            }
    }
}
