package com.springBoot.fifa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.Match;
import domain.MatchTicket;
import domain.Order;
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
import java.util.stream.Stream;

import service.SoccerService;
import validator.OrderValidation;

@SpringBootTest
class FifaControllerMockTest {

	private final static String URI = "/fifa";

	private static final List<String> STADIUMS = getStadiums();
	private static final Stadium CHOSEN_STADIUM = getChosenStadium();
	private static final Match MATCH = getMatch();
	private static final Map<String, MatchTicket> MATCH_TICKETS = getMatchTickets();

	private static String CORRECT_EMAIL = "test.user@outlook.com";
	private static Integer CORRECT_AMOUNT = 1;
	private static Integer CORRECT_SOCCERCODE_1 = 10;
	private static Integer CORRECT_SOCCERCODE_2 = 20;

	private FifaController controller;
	private MockMvc mockMvc;

	@Mock
	private SoccerService mockSoccerService;

	@Autowired
	private OrderValidation orderValidation;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new FifaController();
		mockMvc = standaloneSetup(controller).build();

		ReflectionTestUtils.setField(controller, "soccerService", mockSoccerService);
		ReflectionTestUtils.setField(controller, "orderValidation", orderValidation);
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

	private static Stream<Arguments> invalidOrders() {
		return Stream.of(Arguments.of("", CORRECT_AMOUNT, CORRECT_SOCCERCODE_1, CORRECT_SOCCERCODE_2),
				Arguments.of(" ", CORRECT_AMOUNT, CORRECT_SOCCERCODE_1, CORRECT_SOCCERCODE_2),
				Arguments.of("user", CORRECT_AMOUNT, CORRECT_SOCCERCODE_1, CORRECT_SOCCERCODE_2),
				Arguments.of(CORRECT_EMAIL, -1, CORRECT_SOCCERCODE_1, CORRECT_SOCCERCODE_2),
				Arguments.of(CORRECT_EMAIL, 0, CORRECT_SOCCERCODE_1, CORRECT_SOCCERCODE_2),
				Arguments.of(CORRECT_EMAIL, 26, CORRECT_SOCCERCODE_1, CORRECT_SOCCERCODE_2),
				Arguments.of(CORRECT_EMAIL, -1, 20, 2));
	}

	private Order createOrder(String email, Integer amount, Integer soccerCode1, Integer soccerCode2) {
		Order order = new Order();

		order.setEmail(email);
		order.setAmount(amount);
		order.setSoccerCode1(soccerCode1);
		order.setSoccerCode2(soccerCode2);

		return order;
	}

	private MatchTicket mockMatchTicket(String type) {
		MatchTicket matchTicket = MATCH_TICKETS.get(type);
		Mockito.when(mockSoccerService.getMatch(matchTicket.getMatch().getId())).thenReturn(matchTicket);
		return matchTicket;
	}

	// 2] Tests.
	@Test
	public void testGet_showStadiums() throws Exception {
		Mockito.when(mockSoccerService.getStadiumList()).thenReturn(STADIUMS);

		mockMvc.perform(get(URI)).andExpect(status().isOk()).andExpect(view().name("select_stadium"))
				.andExpect(model().attributeExists("chosenStadium"))
				.andExpect(model().attribute("stadiumList", STADIUMS));
	}

	@Test
	public void testPost_showOverviewPage() throws Exception {
		List<MatchTicket> matchTickets = new ArrayList<>(Arrays.asList(MATCH_TICKETS.get("REGULAR")));

		Mockito.when(mockSoccerService.getMatchesByStadium(CHOSEN_STADIUM.getValue())).thenReturn(matchTickets);

		mockMvc.perform(post(URI).flashAttr("chosenStadium", CHOSEN_STADIUM)).andExpect(status().isOk())
				.andExpect(view().name("matches/list")).andExpect(model().attribute("chosenStadium", CHOSEN_STADIUM))
				.andExpect(model().attribute("matchList", matchTickets));
	}

	@Test
	public void testGet_showOrderPage_matchExists() throws Exception {
		MatchTicket matchTicket = mockMatchTicket("REGULAR");

		mockMvc.perform(get(URI + "/" + matchTicket.getMatch().getId())).andExpect(status().isOk())
				.andExpect(view().name("matches/order")).andExpect(model().attribute("matchTicket", matchTicket))
				.andExpect(model().attributeExists("order"));
	}

	@Test
	public void testGet_showOrderPage_matchDoesNotExist() throws Exception {
		String unknownId = "99";
		Mockito.when(mockSoccerService.getMatch(unknownId)).thenReturn(null);

		mockMvc.perform(get(URI + "/" + unknownId)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/fifa"));
	}

	@Test
	public void testGet_showOrderPage_matchSoldOut() throws Exception {
		MatchTicket matchTicket = mockMatchTicket("SOLDOUT");

		mockMvc.perform(get(URI + "/" + matchTicket.getMatch().getId())).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/fifa?uitverkocht=true"));
	}

	@Test
	public void testPost_onPurchase_validInformation() throws Exception {
		MatchTicket matchTicket = mockMatchTicket("REGULAR");
		Order order = createOrder(CORRECT_EMAIL, CORRECT_AMOUNT, CORRECT_SOCCERCODE_1, CORRECT_SOCCERCODE_2);

		mockMvc.perform(post(URI + "/" + matchTicket.getMatch().getId()).flashAttr("order", order))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/fifa?verkocht=" + CORRECT_AMOUNT));
	}

	@Test
	public void testPost_onPurchase_matchSoldOut() throws Exception {
		MatchTicket matchTicket = mockMatchTicket("REGULAR");
		Order order = createOrder(CORRECT_EMAIL, 25, CORRECT_SOCCERCODE_1, CORRECT_SOCCERCODE_2);

		mockMvc.perform(post(URI + "/" + matchTicket.getMatch().getId()).flashAttr("order", order))
				.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/fifa?uitverkocht=true"));
	}

	@ParameterizedTest
	@MethodSource("invalidOrders")
	public void testPost_onPurchase_invalidInformation(String email, Integer amount, Integer soccerCode1,
			Integer soccerCode2) throws Exception {
		MatchTicket matchTicket = mockMatchTicket("REGULAR");
		Order order = createOrder(email, amount, soccerCode1, soccerCode2);

		mockMvc.perform(post(URI + "/" + matchTicket.getMatch().getId()).flashAttr("order", order))
				.andExpect(status().isOk()).andExpect(model().attribute("matchTicket", matchTicket))
				.andExpect(view().name("matches/order"));
	}

}
