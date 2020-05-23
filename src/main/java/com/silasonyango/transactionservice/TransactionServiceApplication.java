package com.silasonyango.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaAuditing
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {

			final String clientUrl = "*";
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/user_roles/*").allowedOrigins(clientUrl);
				registry.addMapping("/roles/*").allowedOrigins(clientUrl);
				registry.addMapping("/access_privileges/*").allowedOrigins(clientUrl);
				registry.addMapping("/user_access_privileges/*").allowedOrigins(clientUrl);
				registry.addMapping("/users/*").allowedOrigins(clientUrl);
				registry.addMapping("/students/*").allowedOrigins(clientUrl);
				registry.addMapping("/statements/*").allowedOrigins(clientUrl);
				registry.addMapping("/usersessionactivities/*").allowedOrigins(clientUrl);
				registry.addMapping("/sessionlogs/*").allowedOrigins(clientUrl);
				registry.addMapping("/sessionactivities/*").allowedOrigins(clientUrl);
				registry.addMapping("/class-fee-structure-component/*").allowedOrigins(clientUrl);
				registry.addMapping("/student-fee-component/*").allowedOrigins(clientUrl);
				registry.addMapping("/installments/*").allowedOrigins(clientUrl);
			}
		};
	}

}
