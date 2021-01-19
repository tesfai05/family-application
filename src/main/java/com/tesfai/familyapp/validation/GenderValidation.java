package com.tesfai.familyapp.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidation implements ConstraintValidator<ValidGender, String> {

	private static final List<String> GENDER;
	private  String genderType;
	static {

		GENDER = new ArrayList<>();
		GENDER.addAll(Arrays.asList("MALE", "FEMALE"));

	}


	public String getGenderType() {
		return genderType;
	}

	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}

	@Override
	public void initialize(ValidGender constraintAnnotation) {

		this.setGenderType(constraintAnnotation.genderType());

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		List<String> genderList = GenderValidation.GENDER;
		if (genderList == null || genderList.isEmpty()) {

			return false;

		}

		for (String g : genderList) {

			if (g.equalsIgnoreCase(value)) {

				return true;
			}
		}
		return false;

	}

}
