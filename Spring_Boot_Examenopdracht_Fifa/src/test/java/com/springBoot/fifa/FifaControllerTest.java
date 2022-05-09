package com.springBoot.fifa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
class FifaControllerTest {

	private final static String URL = "/fifa";

	private final static String STADIUM_NAME = "Al Bayt Stadium";
	private final static String MATCH_ID = "3";

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeEach
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGet_showStadiums() throws Exception {
		mockMvc.perform(get(URL)).andExpect(status().isOk()).andExpect(view().name("select_stadium"))
				.andExpect(model().attributeExists("stadiumList"));
	}
	
	@Test
	public void testGet_showOrderPage() throws Exception {
		mockMvc.perform(post(URL + "/" + MATCH_ID).param("chosenStadium", STADIUM_NAME)).andExpect(status().isOk())
				.andExpect(view().name("matches/order")).andExpect(model().attributeExists("order"));
	}
	
	@Test
	public void testPost_showOverviewPage() throws Exception {

		mockMvc.perform(post(URL).param("chosenStadium", STADIUM_NAME)).andExpect(status().isOk())
				.andExpect(view().name("matches/list")).andExpect(model().attributeExists("chosenStadium"))
				.andExpect(model().attributeExists("matchList"));
	}



//	@Test 
//	public void testPost_onPurchase() throws Exception {
//		
//	}
}
