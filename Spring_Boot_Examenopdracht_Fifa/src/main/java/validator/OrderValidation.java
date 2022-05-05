package validator;

import java.util.Arrays;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import domain.Order;

public class OrderValidation implements Validator {

	private final static String[] LABELS = new String[] { "amount", "soccerCode1", "soccerCode2" };

	@Override
	public boolean supports(Class<?> clazz) {
		return Order.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Order order = (Order) target;
		
		Arrays.stream(LABELS).forEach(label -> {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, label, "required.item",
					String.format("%s required", label));
		});

		if (order.getSoccerCode1() >= order.getSoccerCode2()) {
			errors.rejectValue("soccerCode1", "soccercodes.constraint", "soccerCode1 < soccerCode2");
		}
	}
}
