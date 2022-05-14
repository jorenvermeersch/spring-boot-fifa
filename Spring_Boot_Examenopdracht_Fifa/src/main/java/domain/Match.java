package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matches")
public class Match implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id; // Unique key. 

	private String[] countries; // The two countries participating in the match. 
	private LocalDate day; // Day of the match. 
	private LocalTime hour; // Hour of the match. 

	public Match() {
		// Used by JPA.
	}

	public Match(String id, String[] countries, LocalDate day, LocalTime hour) {
		this.id = id;
		this.countries = countries;
		this.day = day;
		this.hour = hour;
	}

	public String getId() {
		return id;
	}

	public String[] getCountries() {
		return countries;
	}

	public LocalDate getDay() {
		return day;
	}

	public LocalTime getHour() {
		return hour;
	}

	@Override
	public String toString() {
		return String.format("%s vs %s", countries[0], countries[1]);
	}
}
