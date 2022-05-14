package repository;

import java.util.List;

import domain.MatchTicket;
import domain.Stadium;

public class StadiumDaoJPA extends GenericDaoJPA<Stadium> implements StadiumDao {

	public StadiumDaoJPA(Class<Stadium> type) {
		super(type);
	}

	@Override
	public List<MatchTicket> getMatchesById(String id) {
		throw new UnsupportedOperationException(); 
	}

}
