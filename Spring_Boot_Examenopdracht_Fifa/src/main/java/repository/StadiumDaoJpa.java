package repository;

import java.util.List;

import javax.persistence.TypedQuery;

import domain.MatchTicket;
import domain.Stadium;

public class StadiumDaoJpa extends GenericDaoJpa<Stadium> implements StadiumDao {

	public StadiumDaoJpa(Class<Stadium> type) {
		super(type);
	}

	@Override
	public List<MatchTicket> getMatchesByName(String name) {
		TypedQuery<MatchTicket> query = entityManager.createNamedQuery("Stadium.getMatchesByName", MatchTicket.class).setParameter("name", name); 
		return query.getResultList();
	}

}
