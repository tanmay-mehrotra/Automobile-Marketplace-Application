package com.exception;

@SuppressWarnings("serial")
public class InvalidProspectiveCustomerIdException extends Exception {
	
	public InvalidProspectiveCustomerIdException()
	{
		super("Invalid prospective customer Id");
	}

}
