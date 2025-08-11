package com.parking.ms_parking.notifications;

import com.parking.ms_parking.security.activations.Activation;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Slf4j
@Component
public class EmailsService {
    public void send(Map<String, String> parameters) {
        String message = this.buildMessage(parameters);
        log.info("The message is: {}", message);

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
