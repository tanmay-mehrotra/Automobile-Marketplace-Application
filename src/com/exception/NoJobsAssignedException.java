package com.exception;

@SuppressWarnings("serial")
public class NoJobsAssignedException extends Exception{
	
	public NoJobsAssignedException()
	{
		super("No jobs assigned for the current marketing executive");
	}

}
