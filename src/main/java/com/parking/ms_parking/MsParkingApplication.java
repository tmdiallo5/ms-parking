package com.parking.ms_parking;


import com.parking.ms_parking.security.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@EnableConfigurationProperties(RsaKeys.class)
@SpringBootApplication(
		exclude = {
				SecurityAutoConfiguration.class,
				ManagementWebSecurityAutoConfiguration.class
		}
)
public class MsParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsParkingApplication.class, args);
	}

}
