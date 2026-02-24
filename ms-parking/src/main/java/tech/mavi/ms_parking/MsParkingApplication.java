package tech.mavi.ms_parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.boot.security.autoconfigure.actuate.web.servlet.ManagementWebSecurityAutoConfiguration;

@SpringBootApplication(
		exclude = {

				ManagementWebSecurityAutoConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class,
				DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class
		}
)
public class MsParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsParkingApplication.class, args);
	}

}
