package domain;

public class Order {

	private String email;
	private int amount, soccerCode1, soccerCode2;

	// Setters.
	public void setEmail(String email) {
		this.email = email;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setSoccerCode1(int soccerCode1) {
		this.soccerCode1 = soccerCode1;
	}

	public void setSoccerCode2(int soccerCode2) {
		this.soccerCode2 = soccerCode2;
	}

	// Getters.
	public String getEmail() {
		return email;
	}

	public int getAmount() {
		return amount;
	}

	public int getSoccerCode1() {
		return soccerCode1;
	}

	public int getSoccerCode2() {
		return soccerCode2;
	}

}
