package com.springBoot.fifa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import domain.MatchTicket;
import domain.Stadium;
import repository.MatchTicketDao;
import repository.MatchTicketDaoJPA;
import repository.StadiumDao;
import repository.StadiumDaoJPA;
import seeder.DatabaseSeeder;
import service.SoccerService;
import service.SoccerServiceSQL;
import validator.OrderValidation;

@SpringBootApplication
public class SpringBootExamenopdrachtFifaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExamenopdrachtFifaApplication.class, args);
	}
	
	@Bean
	public OrderValidation orderValidation() {
		return new OrderValidation(); 
	}
	
	@Bean
	public StadiumDao stadiumDao() {
		return new StadiumDaoJPA(Stadium.class);
	}
	
	@Bean
	public MatchTicketDao matchTicketDao() {
		return new MatchTicketDaoJPA(MatchTicket.class);
	}
	
	@Bean 
	public SoccerService soccerService() {
		return new SoccerServiceSQL();
	}
	
	@Bean
	public DatabaseSeeder seeder() {
		return new DatabaseSeeder();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("resources/css/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("resources/scripts/");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/403").setViewName("access_denied");
	}

}
