package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.MatchTicket;
import repository.MatchTicketDaoJPA;
import repository.StadiumDaoJPA;

@Repository
public class SoccerServiceJPA implements SoccerService {
	
	@Autowired
	private StadiumDaoJPA stadiumDao;
	
	@Autowired
	private MatchTicketDaoJPA matchTicketDao; 
	
	@Override
	public List<String> getStadiumList() {
		return null;
	}

	@Override
	public List<MatchTicket> getMatchesByStadium(String stadium) {
		return null;
	}

	@Override
	public MatchTicket getMatch(String id) {
		return null;
	}

	@Override
	public int orderTickets(String id, int amount) {
		return 0;
	}

}
