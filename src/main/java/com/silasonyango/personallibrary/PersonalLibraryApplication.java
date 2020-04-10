package com.silasonyango.personallibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaAuditing
public class PersonalLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalLibraryApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {

			final String clientUrl = "*";
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/user/*").allowedOrigins(clientUrl);
				registry.addMapping("/resource_types/*").allowedOrigins(clientUrl);
				registry.addMapping("/library_partitions/*").allowedOrigins(clientUrl);
				registry.addMapping("/subpartitions/*").allowedOrigins(clientUrl);
				registry.addMapping("/library_fields/*").allowedOrigins(clientUrl);
				registry.addMapping("/resource_brands/*").allowedOrigins(clientUrl);
			}
		};
	}

}
