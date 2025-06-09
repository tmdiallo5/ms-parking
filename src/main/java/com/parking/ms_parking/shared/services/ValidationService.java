package com.parking.ms_parking.shared.services;

import org.springframework.stereotype.Component;

@Component
public class ValidationService {

    public void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("The email is required");
        }
        if(!email.contains("@") || email.indexOf("@") == email.length()-1 || email.indexOf("@") == 0) {
            throw new RuntimeException("The email is not valid");
        }
    }
}
