package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Order;

public class OrderValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Order.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Order order = (Order) target;
		
		if (order.getSoccerCode1() >= order.getSoccerCode2()) {
			errors.rejectValue("soccerCode1", "", "soccerCode1 must be smaller than soccerCode2");
		}	
	}
}
