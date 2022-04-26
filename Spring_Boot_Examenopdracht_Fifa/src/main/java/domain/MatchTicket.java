package domain;

// Amount of tickets available for the match. 
public class MatchTicket {

    private Match match; 
    private int tickets; // Amount of tickets available. 

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
