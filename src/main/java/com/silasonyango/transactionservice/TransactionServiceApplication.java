package com.silasonyango.transactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
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
				registry.addMapping("/actual_terms/*").allowedOrigins(clientUrl);
				registry.addMapping("/actual_weeks/*").allowedOrigins(clientUrl);
				registry.addMapping("/carry_forwards/*").allowedOrigins(clientUrl);
				registry.addMapping("/term_iterations/*").allowedOrigins(clientUrl);
				registry.addMapping("/system_initialization/*").allowedOrigins(clientUrl);
				registry.addMapping("/class_fee_structure_breakdown/*").allowedOrigins(clientUrl);
				registry.addMapping("/change_student_residence/*").allowedOrigins(clientUrl);
				registry.addMapping("/student_residence/*").allowedOrigins(clientUrl);
				registry.addMapping("/residence_swap_type/*").allowedOrigins(clientUrl);
				registry.addMapping("/residence_swap/*").allowedOrigins(clientUrl);
				registry.addMapping("/week_iterations/*").allowedOrigins(clientUrl);
				registry.addMapping("/class_fee_structure/*").allowedOrigins(clientUrl);
				registry.addMapping("/fee_structure/*").allowedOrigins(clientUrl);
				registry.addMapping("/ingestor/*").allowedOrigins(clientUrl);
				registry.addMapping("/fee_corrections/*").allowedOrigins(clientUrl);
				registry.addMapping("/statements/*/*").allowedOrigins(clientUrl);
				registry.addMapping("/academic-classes/*/*").allowedOrigins(clientUrl);
			}
		};
	}

}
