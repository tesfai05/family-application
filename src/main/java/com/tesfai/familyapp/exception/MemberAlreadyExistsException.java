package com.tesfai.familyapp.exception;

public class MemberAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public MemberAlreadyExistsException(String message) {
		super(message);
	}
}
