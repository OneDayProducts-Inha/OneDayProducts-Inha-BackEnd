package inha.app.MyGate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyGateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyGateApplication.class, args);
	}

}
