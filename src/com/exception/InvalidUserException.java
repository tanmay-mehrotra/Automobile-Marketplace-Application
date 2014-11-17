package com.exception;

@SuppressWarnings("serial")
public class InvalidUserException extends Exception{

	public InvalidUserException(String str) {
		super(str);

	}
	public InvalidUserException() {
		super();

	}

}
