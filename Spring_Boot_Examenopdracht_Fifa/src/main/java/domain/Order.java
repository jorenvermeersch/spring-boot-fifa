package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Match match;
	
	@NotEmpty(message = "{validation.required}")
	@Email(message = "{validation.email.invalid}")
	private String email;
	
	@NotNull(message = "{validation.required}")
	@Min(value = 1, message = "{validation.amount.min}")
	@Max(value = 25, message = "{validation.amount.max}")
	private Integer amount = 1; // Number of tickets. 
	
	@NotNull(message = "{validation.required}")
	@Min(1)
	private Integer soccerCode1 = 10;
	
	@NotNull(message = "{validation.required}")
	@Min(1)
	private Integer soccerCode2 = 20; 
	
	public Order() {
		// Used by JPA.
	}
	
	
	// Setters.
	public void setEmail(String email) {
		this.email = email;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setSoccerCode1(Integer soccerCode1) {
		this.soccerCode1 = soccerCode1;
	}

	public void setSoccerCode2(Integer soccerCode2) {
		this.soccerCode2 = soccerCode2;
	}

	// Getters.
	public String getEmail() {
		return email;
	}

	public Integer getAmount() {
		return amount;
	}

	public Integer getSoccerCode1() {
		return soccerCode1;
	}

	public Integer getSoccerCode2() {
		return soccerCode2;
	}

}
