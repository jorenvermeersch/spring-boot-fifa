package service;

import java.util.List;

import domain.MatchTicket;

public interface SoccerService {

	public List<String> getStadiumList();

	public List<MatchTicket> getMatchesByStadium(String stadium);

	public MatchTicket getMatch(String matchId);

	public int orderTickets(String matchId, int amount);

}