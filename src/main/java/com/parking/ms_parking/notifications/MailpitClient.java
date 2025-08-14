package com.parking.ms_parking.notifications;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

public interface MailpitClient {
    @PostExchange("/api/v1/send")
    void send(@RequestBody Map<String, Object> data);


}
