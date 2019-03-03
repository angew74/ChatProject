package com.deltasi.chat.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories("com.deltasi.chat.repository")
@EnableConfigurationProperties
@EnableTransactionManagement
@EntityScan(basePackages = {"com.deltasi.chat.model"})  // scan JPA entities
@ComponentScan(basePackages = {"com.deltasi.chat.services", "com.deltasi.chat.controllers", "com.deltasi.chat.repository" ,"com.deltasi.chat.config"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}

