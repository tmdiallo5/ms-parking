package tech.mavi.ms_parking.notifications;

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

@Slf4j
@Component
public class EmailsServce {

    private MailpitClient mailpitClient;
    String senderEmail = "parkSlotPilot.com";
    String senderName = "Thierno of parkSlotPilot.com";

    public EmailsServce(MailpitClient mailpitClient) {
        this.mailpitClient = mailpitClient;
    }

    public void send(Map<String, String> parameters){
        String message = this.buildEmail(parameters);


       Map<String, Object> emailsParameters =  Map.of(
                "Subject", "Your actions code",
                    "HTML", message,
                    "Text", message,
                    "From", Map.of("Email", senderEmail, "Name", senderName),
                    "To", List.of(Map.of("Email", parameters.get("email"), "Name", parameters.get("name")))
        );
       this.mailpitClient.send(emailsParameters);
    }

    public String buildEmail(Map<String, String> parameters){
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(EmailsServce.class, "/templates");
        configuration.setObjectWrapper(new DefaultObjectWrapper());

        try {
            Template template = configuration.getTemplate(parameters.get("template"));
            StringWriter stringWriter = new StringWriter();
            Map<String, String> templateParameters = Map.of("name", parameters.get("name"), "code", parameters.get("code"));
            template.process(templateParameters, stringWriter);
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
