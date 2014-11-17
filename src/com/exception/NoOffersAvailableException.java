package com.exception;

@SuppressWarnings("serial")
public class NoOffersAvailableException extends Exception{
	
	public NoOffersAvailableException()
	{
	  super("No offer available currently");
    }
}
