package com.parking.ms_parking.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeys(
            RSAPrivateKey rsaPrivateKey,
        RSAPublicKey rsaPublicKey
) {
}
