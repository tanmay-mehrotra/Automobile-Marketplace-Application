package com.exception;

@SuppressWarnings("serial")
public class InvalidCustomerIdException extends Exception {

	public InvalidCustomerIdException(){
		super("Invalid Customer Id");
	}
}
