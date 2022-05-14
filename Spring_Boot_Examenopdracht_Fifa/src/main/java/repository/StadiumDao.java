package repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import domain.MatchTicket;
import domain.Stadium;

public interface StadiumDao extends GenericDao<Stadium> {
	
	@Transactional(readOnly = true)
	public List<MatchTicket> getMatchesByName(String name);

}
