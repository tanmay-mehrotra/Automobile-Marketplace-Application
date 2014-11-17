package com.exception;

@SuppressWarnings("serial")
public class InvalidQuotationIdException extends Exception{
	
	public InvalidQuotationIdException()
	{
		super("Invalid quotation Id");
	}

}
