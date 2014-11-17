
package com.exception;

@SuppressWarnings("serial")
public class NoCarsBookedException extends Exception{

	/**
	 * @Date 12th Feb 2010
	 * @author Abhinav Saini
	 * @Description NoCarsBookedException Class
	 */

	public NoCarsBookedException(){
		super("No Cars Booked ");
	}
}
