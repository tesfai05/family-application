package com.tesfai.familyapp.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.tesfai.familyapp.validation.FieldMatch;

import lombok.Data;

@Data
@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
		@FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match") })

public class UserRegistrationDto {

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@Email
	@NotEmpty
	private String email;

	@Email
	@NotEmpty
	private String confirmEmail;

	@AssertTrue
	private Boolean terms;

}
