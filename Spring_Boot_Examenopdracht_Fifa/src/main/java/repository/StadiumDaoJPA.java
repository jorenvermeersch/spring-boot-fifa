package repository;

import java.util.List;

import javax.persistence.TypedQuery;

import domain.MatchTicket;
import domain.Stadium;

public class StadiumDaoJPA extends GenericDaoJPA<Stadium> implements StadiumDao {

	public StadiumDaoJPA(Class<Stadium> type) {
		super(type);
	}

	@Override
	public List<MatchTicket> getMatchesByName(String name) {
		TypedQuery<MatchTicket> query = entityManager.createNamedQuery("Stadium.getMatchesByName", MatchTicket.class); 
		return query.getResultList();
	}

}
