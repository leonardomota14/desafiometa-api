package com.meta.desafiometa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EntityScan(basePackages = "com.meta.desafiometa.*")
@ComponentScan(basePackages = "com.meta.desafiometa.*")
@SpringBootApplication(scanBasePackages = "com.meta.desafiometa.*")
@EnableJpaRepositories(basePackages = "com.meta.desafiometa.repository")
public class App {

	public static void main(final String[] args) {
		SpringApplication.run(App.class, args);
	}
}
