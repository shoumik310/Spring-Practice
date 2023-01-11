package com.bajajfinserve.orders.constraints.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bajajfinserve.orders.constraints.CreditCard;

public class CreditCardValidator implements ConstraintValidator<CreditCard, String>{

	@Override
	public boolean isValid(String creditCard, ConstraintValidatorContext context) {
		if ( creditCard.length() > 5) {
			return true;
		}
		return false;
	}

}
