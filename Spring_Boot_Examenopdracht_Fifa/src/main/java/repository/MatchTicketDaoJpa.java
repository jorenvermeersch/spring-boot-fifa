package repository;

import javax.persistence.TypedQuery;

import domain.MatchTicket;

public class MatchTicketDaoJpa extends GenericDaoJpa<MatchTicket> implements MatchTicketDao {

	public MatchTicketDaoJpa(Class<MatchTicket> type) {
		super(type);
	}

	@Override
	public MatchTicket getById(String id) {
		TypedQuery<MatchTicket> query = entityManager.createNamedQuery("MatchTicket.getById", MatchTicket.class)
				.setParameter("id", id);
		return query.getSingleResult();
	}

}
