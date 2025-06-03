package com.inpad.spring.inpadspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication()
@PropertySource(value = "classpath:application.yml")
public class InpadSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(InpadSpringBootApplication.class, args);
	}
}
