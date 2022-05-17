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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import domain.MatchTicket;
import domain.Order;
import domain.Stadium;
import service.SoccerService;
import validator.OrderValidation;

@RequestMapping("/fifa")
@Controller
@SessionAttributes(names = { "chosenStadium" })
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
	public String showStadiums(@RequestParam(name = "verkocht", required = false) Integer purchased,
			@RequestParam(name = "uitverkocht", required = false) Boolean soldout, Model model) {
		
		model.addAttribute("stadiumList", soccerService.getStadiumList());
		return "select_stadium";
	}

	@GetMapping(value = "/{id}")
	public String showOrderPage(@PathVariable String id, Model model) {
		MatchTicket matchTicket = soccerService.getMatch(id);
		
		if (matchTicket == null) {
			return "redirect:/fifa";
		}
		
		// Match is sold out. 
		if (matchTicket.soldOut()) {
			return "redirect:/fifa?uitverkocht=true";
		}
		
		model.addAttribute("matchTicket", matchTicket);
		model.addAttribute("order", new Order());
		return "matches/order";
	}

	@PostMapping
	public String showOverviewPage(@ModelAttribute("chosenStadium") Stadium chosenStadium, Model model) {
		model.addAttribute("matchList", soccerService.getMatchesByStadium(chosenStadium.getName()));
		return "matches/list";
	}

	@PostMapping(value = "/{id}")
	public String onPurchase(@PathVariable String id, @Valid Order order, BindingResult result, Model model) {
		MatchTicket matchTicket = soccerService.getMatch(id);

		// Validation: Empty integers + validation soccerCode1 < soccerCode2.
		orderValidation.validate(order, result);

		if (result.hasErrors()) {
			model.addAttribute("matchTicket", matchTicket);
			return "matches/order";
		}

		int amount = order.getAmount();

		if (matchTicket.getTickets() - amount >= 0) {
			soccerService.orderTickets(id, amount);
			
			
			return "redirect:/fifa?verkocht=" + amount;
		}

		return "redirect:/fifa?uitverkocht=true";
	}
}
