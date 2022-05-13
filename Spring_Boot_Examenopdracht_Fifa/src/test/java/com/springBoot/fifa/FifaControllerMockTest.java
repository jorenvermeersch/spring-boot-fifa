package com.springBoot.fifa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.Match;
import domain.MatchTicket;
import domain.Stadium;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.SoccerService;

class FifaControllerMockTest {

	private final static String URL = "/fifa";

	private static final List<String> STADIUMS = getStadiums();
	private static final Stadium CHOSEN_STADIUM = getChosenStadium();
	private static final Match MATCH = getMatch();
	private static final Map<String, MatchTicket> MATCH_TICKETS = getMatchTickets();

	private FifaController controller;
	private MockMvc mockMvc;

	@Mock
	private SoccerService mockSoccerService;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new FifaController();
		mockMvc = standaloneSetup(controller).build();

		ReflectionTestUtils.setField(controller, "soccerService", mockSoccerService);
	}

	// 1] Helper functions.
	private static List<String> getStadiums() {
		return new ArrayList<>(Arrays.asList(new String[] { "Al Bayt Stadium", "Al Thumama Stadium" }));
	}
	private static Stadium getChosenStadium() {
		String stadiumName = STADIUMS.get(0);
		Stadium chosenStadium = new Stadium();
		chosenStadium.setValue(stadiumName);
		
		return chosenStadium;
	}

	private static Match getMatch() {
		return new Match("1", new String[] { "België", "Canada" }, LocalDate.of(2022, 7, 26), LocalTime.of(21, 0));
	}

	private static Map<String, MatchTicket> getMatchTickets() {
		Map<String, MatchTicket> types = new HashMap<>();

		types.put("REGULAR", new MatchTicket(MATCH, 5));
		types.put("SOLDOUT", new MatchTicket(MATCH, 0));

		return types;
	}

	// 2] Tests.
	@Test
	public void testGet_showStadiums() throws Exception {
		Mockito.when(mockSoccerService.getStadiumList()).thenReturn(STADIUMS);

		mockMvc.perform(get(URL)).andExpect(status().isOk()).andExpect(view().name("select_stadium"))
				.andExpect(model().attributeExists("chosenStadium"))
				.andExpect(model().attribute("stadiumList", STADIUMS));
	}

	@Test
	public void testPost_showOverviewPage() throws Exception {
		List<MatchTicket> matchTickets = new ArrayList<>(Arrays.asList(MATCH_TICKETS.get("REGULAR")));

		Mockito.when(mockSoccerService.getMatchesByStadium(CHOSEN_STADIUM.getValue())).thenReturn(matchTickets);

		mockMvc.perform(post(URL).flashAttr("chosenStadium", CHOSEN_STADIUM)).andExpect(status().isOk())
				.andExpect(view().name("matches/list")).andExpect(model().attribute("chosenStadium", CHOSEN_STADIUM))
				.andExpect(model().attribute("matchList", matchTickets));
	}

	@Test
	public void testGet_showOrderPage_matchExists() throws Exception {
		MatchTicket matchTicket = MATCH_TICKETS.get("REGULAR");
		Mockito.when(mockSoccerService.getMatch(matchTicket.getMatch().getId())).thenReturn(matchTicket);

		mockMvc.perform(get(URL + "/" + matchTicket.getMatch().getId())).andExpect(status().isOk())
				.andExpect(view().name("matches/order")).andExpect(model().attribute("matchTicket", matchTicket))
				.andExpect(model().attributeExists("order"));
	}

	@Test
	public void testGet_showOrderPage_matchDoesNotExist() throws Exception {
		String unknownId = "99";
		Mockito.when(mockSoccerService.getMatch(unknownId)).thenReturn(null);

		mockMvc.perform(get(URL + "/" + unknownId)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/fifa"));

	}

	@Test
	public void testGet_showOrderPage_matchSoldOut() throws Exception {
		MatchTicket matchTicket = MATCH_TICKETS.get("SOLDOUT");
		Mockito.when(mockSoccerService.getMatch(matchTicket.getMatch().getId())).thenReturn(matchTicket);

		mockMvc.perform(get(URL + "/" + matchTicket.getMatch().getId())).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/fifa?uitverkocht=true"));

	}
	
	@Test
	public void testPost_onPurchase() throws Exception {
		
	}

}
