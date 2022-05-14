package repository;

import domain.MatchTicket;

public interface MatchTicketDao extends GenericDao<MatchTicket> {

	public int orderTickets(String id, int amount);
}
