package com.exception;

@SuppressWarnings("serial")
public class CarNotAvailableException extends Exception {
	
	public CarNotAvailableException()
	{
		super("Car is currently Not Available");
	}

}
