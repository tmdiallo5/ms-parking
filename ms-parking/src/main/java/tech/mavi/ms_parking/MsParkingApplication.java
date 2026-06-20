package tech.mavi.ms_parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import tech.mavi.ms_parking.security.RsaKeys;

@EnableConfigurationProperties(RsaKeys.class)
@SpringBootApplication
public class MsParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsParkingApplication.class, args);
	}

}
