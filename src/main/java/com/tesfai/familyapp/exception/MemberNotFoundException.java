package com.tesfai.familyapp.exception;


public class MemberNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7945182081427286386L;
	
	public MemberNotFoundException(String message){
		super(message);
	}
}
