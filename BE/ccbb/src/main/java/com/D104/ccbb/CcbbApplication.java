package com.D104.ccbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CcbbApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcbbApplication.class, args);
	}

}
