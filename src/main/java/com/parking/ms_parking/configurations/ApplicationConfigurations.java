package com.parking.ms_parking.configurations;

import com.parking.ms_parking.notifications.MailpitClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ApplicationConfigurations {
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
