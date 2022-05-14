package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


// Amount of tickets available for the match. 
@Entity
@Table(name = "matchtickets")
@NamedQueries({
	@NamedQuery(name = "MatchTicket.getById", query = "SELECT mt FROM MatchTicket mt JOIN Match m ON mt.match = m WHERE m.id = :id")
})
public class MatchTicket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@OneToOne
	private Match match; 
	
	@ManyToOne
	private Stadium stadium;
	
    private int tickets; // Amount of tickets available. 
    
    protected MatchTicket() {
    	// Used by JPA.
    }
    
    public MatchTicket(Match match, int tickets) {
        this.match = match;
        this.tickets = tickets;
    }

    public int getTickets() {
        return tickets;
    }
    
    public Match getMatch() {
        return match;
    }
    
    // Only used for seeding purposes. 
    public void setStadium(Stadium stadium) {
    	this.stadium = stadium;
    }
    
    // We want to buy 'amount' tickets for the match. 
    public int buyTickets(int amount) {
        if (amount <= 0) {
            return -1;
        }
        
        // Check if enough tickets are available. 
        if (tickets >= amount) {
            tickets -= amount;
            return amount;
        }

        // Not enough tickets. 
        int purchased = tickets;
        tickets = 0;
        return purchased;
    }

    public boolean soldOut() {
        return tickets == 0;
    }
}
