package com.springBoot.fifa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.SoccerService;

@RequestMapping("/fifa")
@Controller
public class MatchController {
	
	@Autowired
	private SoccerService soccerService; 
	
	@GetMapping
	public String selectStadium(Model model) {
		model.addAttribute("stadiumList", soccerService.getStadiumList());
		return "stadium_select";
	}
	
	@PostMapping
	public String showOverview(Model model) {
		return "/matches/overview";
	}
}
