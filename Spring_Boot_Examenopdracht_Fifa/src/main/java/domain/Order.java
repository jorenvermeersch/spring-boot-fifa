package domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Order {
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@Min(1)
	@Max(25)
	private int amount = 1;
	
	@NotNull
	@Min(1)
	private int soccerCode1 = 10;
	
	@NotNull
	@Min(1)
	private int soccerCode2 = 20; 

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
