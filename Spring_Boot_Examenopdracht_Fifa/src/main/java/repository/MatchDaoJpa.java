package repository;

import domain.Match;

public class MatchDaoJpa extends GenericDaoJpa<Match> implements MatchDao {

	public MatchDaoJpa(Class<Match> type) {
		super(type);
	}

}
