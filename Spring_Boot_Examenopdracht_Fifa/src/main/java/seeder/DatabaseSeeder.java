package seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {
	
	
	@Autowired
	public DatabaseSeeder() {
		
	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		seedStadiums();
		seedMatches();
		seedMatchTickets();
	}
	
	private void seedStadiums() {
		System.out.println("Stadium table seeded.");
	}
	
	private void seedMatches() {
		System.out.println("Match table seeded.");
	}
	
	private void seedMatchTickets() {
		System.out.println("MatchTicket table seeded.");
	}
}
