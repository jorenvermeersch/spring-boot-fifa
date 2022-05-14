package repository;

import java.util.List;

import domain.MatchTicket;
import domain.Stadium;

public interface StadiumDao extends GenericDao<Stadium> {

	public List<MatchTicket> getMatchesById(String id);

}
