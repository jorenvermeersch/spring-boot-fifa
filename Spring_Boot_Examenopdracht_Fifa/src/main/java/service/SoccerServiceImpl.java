package service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Match;
import domain.MatchTicket;

public class SoccerServiceImpl implements SoccerService {

	private List<String> stadiumList = new ArrayList<>();

	// Without database.

	// mapMatchesByStadium, by stadium, a list of matches.
	private final Map<String, List<MatchTicket>> mapMatchesByStadium = new HashMap<>();

	// mapMatchdById, by id een matchTicket.
	private final Map<String, MatchTicket> mapMatchdById = new HashMap<>();

	public SoccerServiceImpl() {
		// Without database.
		stadiumList = new ArrayList<>(Arrays.asList(new String[] { "Al Bayt Stadium", "Al Thumama Stadium" }));

		mapMatchdById.put("1", new MatchTicket(new Match("1", new String[] { "België", "Canada" }, LocalDate.of(2022, 7, 26), LocalTime.of(21, 0)), 35));
		mapMatchdById.put("2", new MatchTicket(new Match("2", new String[] { "Brazilië", "Zwitserland" }, LocalDate.of(2022, 7, 27), LocalTime.of(18, 0)), 21));
		mapMatchdById.put("3", new MatchTicket(new Match("3", new String[] { "Marroko", "Kroatië" }, LocalDate.of(2022, 7, 28), LocalTime.of(15, 0)), 5));
		mapMatchdById.put("4", new MatchTicket(new Match("4", new String[] { "Spanje", "Duitsland" }, LocalDate.of(2022, 7, 28), LocalTime.of(18, 0)), 95));
		mapMatchdById.put("5", new MatchTicket(new Match("5", new String[] { "Frankrijk", "Denemarken" }, LocalDate.of(2022, 7, 30), LocalTime.of(15, 0)), 45));
		mapMatchdById.put("6", new MatchTicket(new Match("6", new String[] { "Argentinië", "Mexico" }, LocalDate.of(2022, 7, 30), LocalTime.of(18, 0)), 10));
		mapMatchdById.put("7", new MatchTicket(new Match("7", new String[] { "Engeland", "USA" }, LocalDate.of(2022, 7, 31), LocalTime.of(18, 0)), 22));
		mapMatchdById.put("8", new MatchTicket(new Match("8", new String[] { "Nederland", "Qatar" }, LocalDate.of(2022, 7, 31), LocalTime.of(21, 0)), 16));

		mapMatchesByStadium.put(stadiumList.get(0),
				new ArrayList<>(Arrays.asList(new MatchTicket[] { mapMatchdById.get("1"), mapMatchdById.get("2"),
						mapMatchdById.get("3"), mapMatchdById.get("6"), mapMatchdById.get("7") })));

		mapMatchesByStadium.put(stadiumList.get(1), new ArrayList<>(Arrays
				.asList(new MatchTicket[] { mapMatchdById.get("4"), mapMatchdById.get("5"), mapMatchdById.get("8") })));

	}

	public List<String> getStadiumList() {
		return stadiumList;
	}

	// Returns list "tickets per match" according to stadium.
	public List<MatchTicket> getMatchesByStadium(String stadium) {
		return mapMatchesByStadium.get(stadium);
	}

	// Returns amount of tickets for a match given the id of the match.
	public MatchTicket getMatch(String matchId) {
		return mapMatchdById.get(matchId);
	}

	public int orderTickets(String matchId, int amount) {
		MatchTicket matchTicket = mapMatchdById.get(matchId);
		return matchTicket.buyTickets(amount);
	}
}
