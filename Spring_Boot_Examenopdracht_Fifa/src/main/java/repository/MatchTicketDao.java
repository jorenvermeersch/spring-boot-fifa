package repository;

import domain.MatchTicket;

public interface MatchTicketDao extends GenericDao<MatchTicket> {
	
	public MatchTicket getById(String id);
	
	public int orderTickets(String id, int amount);
}
