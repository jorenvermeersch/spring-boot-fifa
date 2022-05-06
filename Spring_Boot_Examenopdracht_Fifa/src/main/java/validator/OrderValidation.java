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

		Integer soccerCode1 = order.getSoccerCode1();
		Integer soccerCode2 = order.getSoccerCode2();

		if (soccerCode1 == null || soccerCode2 == null) {
			return;
		}

		if (soccerCode1 >= soccerCode2) {
			errors.rejectValue("soccerCode1", "soccercodes.constraint", "soccerCode1 < soccerCode2");
		}
	}
}
