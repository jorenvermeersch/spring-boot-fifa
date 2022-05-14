package repository;

import domain.MatchTicket;

public class MatchTicketDaoJPA extends GenericDaoJPA<MatchTicket> implements MatchTicketDao  {

	public MatchTicketDaoJPA(Class<MatchTicket> type) {
		super(type);
	}

	@Override
	public int orderTickets(String id, int amount) {
		throw new UnsupportedOperationException();
	}

}
