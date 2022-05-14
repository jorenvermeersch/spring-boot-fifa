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
public class SoccerServiceJpa implements SoccerService {

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
	public MatchTicket getMatch(String matchId) {
		return matchTicketDao.getById(matchId);
	}

	@Override
	public int orderTickets(String matchId, int amount) {
		MatchTicket matchTicket = this.getMatch(matchId);
		
		int tickets = matchTicket.buyTickets(amount);
		matchTicketDao.update(matchTicket);
		
		return tickets; 
	}

}
