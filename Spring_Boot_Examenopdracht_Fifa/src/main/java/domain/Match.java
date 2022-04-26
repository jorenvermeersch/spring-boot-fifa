package domain;

public class Match {

	private String id; // Unique key. 
	private String[] countries; // The two countries participating in the match. 
	private int day; // Day of the match. 
	private int hour; // Hour of the match. 

	public Match() {
	}

	public Match(String id, String[] countries, int day, int hour) {
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

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	@Override
	public String toString() {
		return String.format("%s vs %s op %d-11", countries[0], countries[1], day);
	}
}
