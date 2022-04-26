package com.springBoot.fifa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		model.addAttribute("stadium", new Stadium());
		return "stadium_select";
	}
	
	@PostMapping
	public String showOverview(@ModelAttribute Stadium stadium, Model model) {
		model.addAttribute("matchList", soccerService.getMatchesByStadium(stadium.getValue()));
		return "/matches/overview";
	}
}
