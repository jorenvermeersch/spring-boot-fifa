package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stadiums")
public class Stadium implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String name; 
	
	@OneToMany(mappedBy = "stadium")
	private List<MatchTicket> matchTickets;
	
	
	public Stadium() {
		// Used by JPA. 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}
}
