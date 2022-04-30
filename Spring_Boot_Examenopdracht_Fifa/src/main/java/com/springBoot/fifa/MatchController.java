package com.springBoot.fifa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.MatchTicket;
import service.SoccerService;

@RequestMapping("/fifa")
@Controller
public class MatchController {
	
	@Autowired
	private SoccerService soccerService; 
	
	@GetMapping
	public String showStadiums(Model model) {
		model.addAttribute("stadiumList", soccerService.getStadiumList());
		model.addAttribute("stadium", new Stadium());
		return "select_stadium";
	}
	
	@GetMapping(value = "/{id}")
	public String showOrderPage(@PathVariable String id, Model model) {
		MatchTicket match = soccerService.getMatch(id);
		if (match == null) {
			return "redirect:/matches/list";
		}
		model.addAttribute("match", match);
		return "matches/order"; 
	}
	
	@PostMapping
	public String showOverviewPage(@ModelAttribute Stadium stadium, Model model) {
		model.addAttribute("matchList", soccerService.getMatchesByStadium(stadium.getValue()));
		return "/matches/list";
	}
}
