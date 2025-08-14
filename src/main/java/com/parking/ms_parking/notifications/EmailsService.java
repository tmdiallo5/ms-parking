package com.parking.ms_parking.notifications;

import com.parking.ms_parking.security.activations.Activation;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Slf4j
@Component
public class EmailsService {
    String senderEmail = "diallo@parking.com";
    String senderName = "Diallo";
    private final MailpitClient mailpitClient;

    public EmailsService(MailpitClient mailpitClient) {
        this.mailpitClient = mailpitClient;
    }

    public void send(Map<String, String> parameters) {

        String message = this.buildMessage(parameters);
     Map<String, Object> emailParameters = Map.of(
                "Subject", "your activation code",
                "HTML", message,
                "text", message,
                "From", Map.of("Email", senderEmail, "name", senderName),
                "To", List.of(Map.of("Email", parameters.get("email"), "name", parameters.get("name")))

        );
     this.mailpitClient.send(emailParameters);

    }

    public String buildMessage(Map<String, String> params) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setObjectWrapper(new DefaultObjectWrapper());

        try {
            Template template = configuration.getTemplate(params.get("template"));
            StringWriter stringWriter = new StringWriter();
           Map<String, String> templateParameters = Map.of("name", params.get("name"), "code", params.get("code"));
            try {
                template.process(templateParameters, stringWriter);
                return stringWriter.toString();
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
