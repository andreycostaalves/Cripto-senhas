package io.github.andreycostaalves.Criptosenhas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CriptosenhasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriptosenhasApplication.class, args);
	}

}
