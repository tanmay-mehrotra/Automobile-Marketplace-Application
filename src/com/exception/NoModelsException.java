
package com.exception;


@SuppressWarnings("serial")
public class NoModelsException extends Exception{
	public NoModelsException(){
		super("No Models Found");
	}
}

