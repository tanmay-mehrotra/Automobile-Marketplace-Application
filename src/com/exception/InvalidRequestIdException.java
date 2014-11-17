package com.exception;

@SuppressWarnings("serial")
public class InvalidRequestIdException extends Exception{
	
	public InvalidRequestIdException()
	{
		super("Invalid request id entered");
	}

}
