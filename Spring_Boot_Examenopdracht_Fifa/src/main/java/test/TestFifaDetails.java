package test;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.springframework.web.reactive.function.client.WebClient;

public class TestFifaDetails {
	
	private final String SERVER_URI = "http://localhost:8080/fifaDetail";
	private WebClient webClient = WebClient.create();
	
	public TestFifaDetails() {
		IntStream.rangeClosed(1, 8).forEach(id -> {
			System.out.printf("GET /fifaDetail/%d -------|%n", id);
			getMatchCountries(id + "");
		});
	}
	
	private void getMatchCountries(String matchId) {
		String[] countries = webClient.get().uri(SERVER_URI + "/" + matchId).retrieve().bodyToMono(String[].class).block();
		System.out.printf("=> %s %n%n", Arrays.toString(countries));	
	}
}
