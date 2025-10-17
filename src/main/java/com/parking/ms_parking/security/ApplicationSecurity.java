package com.parking.ms_parking.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.parking.ms_parking.authentification.AuthentificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class ApplicationSecurity {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private RsaKeys rsaKeys;
    private BCryptPasswordEncoder passwordEncoder;
    private AuthentificationService authentificationService;

    public ApplicationSecurity(AuthenticationManagerBuilder authenticationManagerBuilder, RsaKeys rsaKeys, BCryptPasswordEncoder passwordEncoder, AuthentificationService authentificationService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.rsaKeys = rsaKeys;
        this.passwordEncoder = passwordEncoder;
        this.authentificationService = authentificationService;
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(this.authenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return
                httpSecurity
                        .cors(Customizer.withDefaults())
                        .csrf(AbstractHttpConfigurer::disable)
                        .headers(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(
                                customizer ->
                                        customizer
                                                .requestMatchers(HttpMethod.POST,"/sign-in").permitAll()
                                                .requestMatchers(HttpMethod.POST,"/sign-up").permitAll()
                                                .requestMatchers(HttpMethod.POST,"/activate").permitAll()
                                                .anyRequest().authenticated()
                        )
                        .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                        .build();
    }


    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.authentificationService);
        return daoAuthenticationProvider;
    }

    
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.rsaKeys.rsaPublicKey())
                .build();
    }

    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey
                .Builder(this.rsaKeys.rsaPublicKey())
                .privateKey(this.rsaKeys.rsaPrivateKey())
                .build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }
}
