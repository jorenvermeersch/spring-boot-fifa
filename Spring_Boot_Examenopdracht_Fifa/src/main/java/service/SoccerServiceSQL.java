package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.MatchTicket;
import domain.Stadium;
import repository.MatchTicketDao;
import repository.StadiumDao;

@Repository
public class SoccerServiceSQL implements SoccerService {

	@Autowired
	private StadiumDao stadiumDao;

	@Autowired
	private MatchTicketDao matchTicketDao;

	@Override
	public List<String> getStadiumList() {
		return stadiumDao.findAll().stream().map(Stadium::getName).collect(Collectors.toList());
	}

	@Override
	public List<MatchTicket> getMatchesByStadium(String stadium) {
		return stadiumDao.getMatchesByName(stadium);
	}

	@Override
	public MatchTicket getMatch(String id) {
		return matchTicketDao.getById(id);
	}

	@Override
	public int orderTickets(String id, int amount) {
		MatchTicket matchTicket = this.getMatch(id);
		return matchTicket.buyTickets(amount);
	}

}
