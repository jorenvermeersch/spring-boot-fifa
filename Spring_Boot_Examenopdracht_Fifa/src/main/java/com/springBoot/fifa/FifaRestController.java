package com.springBoot.fifa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.SoccerService;

@RestController
@RequestMapping(value = "/fifaDetail")
public class FifaRestController {
	
	@Autowired
	private SoccerService soccerService;
	
	@GetMapping(value = "/{id}")
	public String[] getMatchCountries(@PathVariable("id") String matchId) {
		return soccerService.getMatch(matchId).getMatch().getCountries(); 
	}
}
