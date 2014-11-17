package com.bean;

import com.exception.InvalidUserException;
import com.wrapper.AutoSellWrapper;

public class ChangePasswordMB {
	private String userName;
	private String oldPassword;
	private String password;
	private String message;



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String changePassword() {
		/*This method will validate the user and then change the password*/
		AutoSellWrapper wrapper=new AutoSellWrapper();
		try {
			/*Calling the changePassword method of manager class using wrapper class object*/
			String result=wrapper.changePassword(this.userName,this.oldPassword,this.password);


			this.setMessage(result);
			/*On successful change of password in the database*/
			return "passwordchanged";

		} catch (InvalidUserException e) {

			/*Exception thrown if user is Invalid*/
			this.setMessage(e.getMessage());

			return "Invalid";
		}



	}
}
