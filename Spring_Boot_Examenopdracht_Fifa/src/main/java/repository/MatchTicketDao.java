package repository;

import org.springframework.transaction.annotation.Transactional;

import domain.MatchTicket;

public interface MatchTicketDao extends GenericDao<MatchTicket> {
	
	@Transactional(readOnly = true)
	public MatchTicket getById(String id);

}
