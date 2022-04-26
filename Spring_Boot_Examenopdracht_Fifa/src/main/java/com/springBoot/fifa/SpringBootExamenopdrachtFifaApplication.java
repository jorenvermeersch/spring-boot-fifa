package com.springBoot.fifa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import service.VoetbalService;
import service.VoetbalServiceImpl;

@SpringBootApplication
public class SpringBootExamenopdrachtFifaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExamenopdrachtFifaApplication.class, args);
	}
	
	@Bean 
	public VoetbalService soccerService() {
		return new VoetbalServiceImpl();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("resources/css/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("resources/scripts/");
	}

}
