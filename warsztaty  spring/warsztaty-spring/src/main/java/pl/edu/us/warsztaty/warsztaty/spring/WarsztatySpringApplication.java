package pl.edu.us.warsztaty.warsztaty.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class WarsztatySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarsztatySpringApplication.class, args);
	}


	@Bean
	Clock clock() {
		return Clock.systemDefaultZone();
	}

}
