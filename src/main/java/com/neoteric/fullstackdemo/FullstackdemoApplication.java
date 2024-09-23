package com.neoteric.fullstackdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.neoteric.fullstackdemo")
public class FullstackdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullstackdemoApplication.class, args);
	}

}
