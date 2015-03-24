package com.excilys.formation.project.utils;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;



@Component
public class DateValidator implements ConstraintValidator<Date, String> {
	@Autowired
	private MessageSource messageSource;

	@Override
	public void initialize(Date constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		if (value.isEmpty()) {
			return true;
		}
		if (Utils.checkDate(value)){
			return true;
		}
		return false;
	}

	private String getDateRegex() {
		Locale userLocale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("binding.date.regex", null, userLocale);
	}
}