package com.exception;

@SuppressWarnings("serial")
public class InvalidChassisNumberException extends Exception{

	public InvalidChassisNumberException() 
	{
		super("Invalid Chassis Number");
	}
	
}
