package tech.mavi.ms_parking.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tech.mavi.ms_parking.notifications.MailpitClient;

@Component
public class ApplicationsConfigurations {

    String mailClientUrl = "http://localhost:8025";

    @Bean
    MailpitClient mailpitClient(){
        RestClient client = RestClient.create(mailClientUrl);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(client))
                .build();
        return httpServiceProxyFactory.createClient(MailpitClient.class);
    }
}
