package com.springBoot.fifa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import domain.Match;
import domain.MatchTicket;
import domain.Stadium;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.MatchDao;
import repository.MatchDaoJpa;
import repository.MatchTicketDao;
import repository.MatchTicketDaoJpa;
import repository.StadiumDao;
import repository.StadiumDaoJpa;
import seeder.DatabaseSeeder;
import service.SoccerService;
import service.SoccerServiceJpa;
import test.TestFifaDetails;
import validator.OrderValidation;

@SpringBootApplication
public class SpringBootExamenopdrachtFifaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExamenopdrachtFifaApplication.class, args);
		new TestFifaDetails();
	}

	@Bean
	public OrderValidation orderValidation() {
		return new OrderValidation();
	}

	@Bean
	public StadiumDao stadiumDao() {
		return new StadiumDaoJpa(Stadium.class);
	}

	@Bean
	public MatchTicketDao matchTicketDao() {
		return new MatchTicketDaoJpa(MatchTicket.class);
	}

	@Bean
	public MatchDao matchDao() {
		return new MatchDaoJpa(Match.class);
	}

	@Bean
	public SoccerService soccerService() {
		return new SoccerServiceJpa();
	}

	@Bean
	public DatabaseSeeder seeder(StadiumDao stadiumDao, MatchTicketDao matchTicketDao, MatchDao matchDao) {
		return new DatabaseSeeder(stadiumDao, matchTicketDao, matchDao);
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
