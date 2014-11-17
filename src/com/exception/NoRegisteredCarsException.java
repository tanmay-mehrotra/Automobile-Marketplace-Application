package com.exception;

@SuppressWarnings("serial")
public class NoRegisteredCarsException extends Exception {
	public NoRegisteredCarsException(){
		super("No Registered Cars");
	}

}
