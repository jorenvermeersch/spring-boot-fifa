package com.springBoot.fifa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import domain.MatchTicket;
import domain.Order;
import domain.Stadium;
import service.SoccerService;
import validator.OrderValidation;

@RequestMapping("/fifa")
@Controller
@SessionAttributes("chosenStadium")
public class FifaController {
	
	// Holds the name of the stadium chosen by the user. 
	@ModelAttribute("chosenStadium")
	public Stadium chosenStadium() {
		return new Stadium(); 
	}
	
	
	@Autowired
	private SoccerService soccerService; 
	
	@Autowired
	private OrderValidation orderValidation; 
	
	@GetMapping
	public String showStadiums(Model model) {
		model.addAttribute("stadiumList", soccerService.getStadiumList());
		return "select_stadium";
	}
	
	@GetMapping(value = "/{id}")
	public String showOrderPage(@PathVariable String id, Model model) {
		MatchTicket matchTicket = soccerService.getMatch(id);
		if (matchTicket == null) {
			return "redirect:/matches/list";
		}
		model.addAttribute("matchTicket", matchTicket);
		model.addAttribute("order", new Order()); 
		return "matches/order"; 
	}
	
	@PostMapping
	public String showOverviewPage(@ModelAttribute("chosenStadium") Stadium chosenStadium, Model model) {
		model.addAttribute("matchList", soccerService.getMatchesByStadium(chosenStadium.getValue()));
		return "/matches/list";
	}
		
	// TODO: Something wrong here. 
	@PostMapping(value = "/order")
	public String onPurchase(@Valid Order order, BindingResult result, Model model) {
		orderValidation.validate(order, result); // Validation soccerCode1 < soccerCode2
		
		if (result.hasErrors()) {
			return "matches/order"; 
		}
		
		MatchTicket ticket = (MatchTicket) model.getAttribute("ticket"); 
		
		if (ticket.getTickets() - order.getAmount() >= 0) {
			ticket.buyTickets(order.getAmount()); 
			return "redirect:matches/list"; 
		}
		
		return "matches/list"; 
	}
}
