package seeder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import domain.Match;
import domain.MatchTicket;
import domain.Stadium;
import repository.MatchDao;
import repository.MatchTicketDao;
import repository.StadiumDao;

@Component
public class DatabaseSeeder {

	private StadiumDao stadiumDao;
	private MatchTicketDao matchTicketDao;
	private MatchDao matchDao;

	@Autowired
	public DatabaseSeeder(StadiumDao stadiumDao, MatchTicketDao matchTicketDao, MatchDao matchDao) {
		this.stadiumDao = stadiumDao;
		this.matchTicketDao = matchTicketDao;
		this.matchDao = matchDao;
	}

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		List<Stadium> stadiums = seedStadiums();
		List<Match> matches = seedMatches();
		seedMatchTickets(stadiums, matches);
	}

	private List<Stadium> seedStadiums() {
		ArrayList<Stadium> stadiums = new ArrayList<>();

		try {
			Stadium s1 = new Stadium("Al Bayt Stadium");
			Stadium s2 = new Stadium("Al Thumama Stadium");
			Stream.of(s1, s2).forEach(stadium -> {
				stadiumDao.insert(stadium);
				stadiums.add(stadium);
			});
			notify("Stadium table succesfully seeded.");

		} catch (Exception exception) {
			System.out.println("Error occured while seeding Stadium table");
			exception.printStackTrace();
		}

		return stadiums;
	}

	private List<Match> seedMatches() {
		List<Match> matches = new ArrayList<>();

		try {
			Match m1 = new Match("1", new String[] { "België", "Canada" }, LocalDate.of(2022, 7, 26),
					LocalTime.of(21, 0));
			Match m2 = new Match("2", new String[] { "Brazilië", "Zwitserland" }, LocalDate.of(2022, 7, 27),
					LocalTime.of(18, 0));
			Match m3 = new Match("3", new String[] { "Marroko", "Kroatië" }, LocalDate.of(2022, 7, 28),
					LocalTime.of(15, 0));
			Match m4 = new Match("4", new String[] { "Spanje", "Duitsland" }, LocalDate.of(2022, 7, 28),
					LocalTime.of(18, 0));
			Match m5 = new Match("5", new String[] { "Frankrijk", "Denemarken" }, LocalDate.of(2022, 7, 30),
					LocalTime.of(15, 0));
			Match m6 = new Match("6", new String[] { "Argentinië", "Mexico" }, LocalDate.of(2022, 7, 30),
					LocalTime.of(18, 0));
			Match m7 = new Match("7", new String[] { "Engeland", "USA" }, LocalDate.of(2022, 7, 31),
					LocalTime.of(18, 0));
			Match m8 = new Match("8", new String[] { "Nederland", "Qatar" }, LocalDate.of(2022, 7, 31),
					LocalTime.of(21, 0));
			Stream.of(m1, m2, m3, m4, m5, m6, m7, m8).forEach(match -> {
				matchDao.insert(match);
				matches.add(match);
			});

			notify("Match table succesfully seeded.");
		} catch (Exception exception) {
			System.out.println("Error occured while seeding Match table");
			exception.printStackTrace();
		}

		return matches;
	}

	private void seedMatchTickets(List<Stadium> stadiums, List<Match> matches) {
		Set<String> matchIds = new HashSet<>(List.of("1", "2", "3", "6", "7"));
		List<Integer> ticketCount = List.of(35, 21, 5, 95, 45, 10, 22, 16);

		try {

			for (int index = 0; index < matches.size(); index++) {
				Match match = matches.get(index);

				MatchTicket matchTicket = new MatchTicket(match, ticketCount.get(index));
				matchTicket.setStadium(matchIds.contains(match.getId()) ? stadiums.get(0) : stadiums.get(1));
				matchTicketDao.insert(matchTicket);
			}
			notify("MatchTicket table succesfully seeded.");

		} catch (Exception exception) {
			System.out.println("Error occured while seeding MatchTicket table");
			exception.printStackTrace();
		}

	}

	private void notify(String content) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String message = String.format("%s  INFO | %s : %s", LocalDateTime.now().format(formatter),
				this.getClass().getSimpleName(), content);
		System.out.println(message);
	}

}
