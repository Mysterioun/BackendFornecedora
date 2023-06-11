package br.ufsm.sci.pi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailUtils {
    @Autowired
    private JavaMailSender emailSender;

   /* public void sendSimpleMessage(String to, String subject, String text, List<String> list){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cafeteriadoscrias@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        if(list != null && list.size() > 0)
             message.setCc(getCcArray(list));
        emailSender.send(message);
    }*/


    /*private String[] getCcArray(List<String> ccList){
        String[] cc = new String[ccList.size()];
        for(int i = 0; i < ccList.size(); i++){
            cc[i] = ccList.get(i);
        }
        return cc;
    }*/

    public void esqueceuEmail(String to, String subject, String senha) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("cafeteriadoscrias@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        // HTML do e-mail
        String htmlMsg = "<html><head><style>"
                + "body {font-family: Arial, sans-serif;}"
                + "h1 {color: #333333;}"
                + "p {line-height: 1.5;}"
                + ".login-link {"
                + "    display: inline-block;"
                + "    padding: 10px 20px;"
                + "    background-color: #d40000;"
                + "    color: #ffffff;"
                + "    text-decoration: none;"
                + "    border-radius: 4px;"
                + "}"
                + ".login-link:hover {background-color: #d40000;}"
                + ".login-link span {color: #ffffff;}"
                + "</style></head><body>"
                + "<h1>Seus dados de login da CaféSolutions</h1>"
                + "<p><strong>Email:</strong> " + to + "</p>"
                + "<p><strong>Senha:</strong> " + senha + "</p>"
                + "<p><a class=\"login-link\" href=\"http://localhost:4200/\">Clique aqui para fazer login</a></p>"
                + "</body></html>";

        message.setContent(htmlMsg, "text/html");
        emailSender.send(message);
    }


}
