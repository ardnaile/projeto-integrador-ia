package JulianoEliandra.TravelAi.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.File;

@Service
public class EmailService {
    private final String username = ""; // Substituir pelo e-mail da agencia logada
    private final String password = ""; // Substituir pela senha da agencia logada

    public void enviarEmailComAnexo(String destinatario, String assunto, String corpo, String caminhoArquivo) throws MessagingException {
        // Configurações do servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "");
        props.put("mail.smtp.port", "");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Criação da mensagem de e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(assunto);

            // Corpo do e-mail
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(corpo);

            // Anexo
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(caminhoArquivo));

            // Juntar corpo e anexo
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Enviar o e-mail
            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");
        } catch (MessagingException | java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}
