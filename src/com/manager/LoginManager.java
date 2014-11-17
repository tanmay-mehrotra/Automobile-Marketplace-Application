package com.manager;

import com.TO.LoginTO;
import com.exception.InvalidUserException;
import com.service.LoginService;

public class LoginManager {

	public LoginTO validatePassword(String userName, String password) throws InvalidUserException
	{
		LoginService loginService= new LoginService();
		LoginTO loginTO = loginService.validatePassword(userName, password);
		return loginTO;
	}
	public boolean changePassword(String userName,String oldPassword, String password) throws InvalidUserException{
		/*this method calls validatePassword() and changePassword() methods for validating the user and changing the password respectively*/
		LoginService loginService=new LoginService();
		LoginTO loginTO=loginService.validatePassword(userName,oldPassword);
		if(loginTO==null){
			/*If userid and password are not valid false is returned*/
			return false;
		}
		else{
			/*If userid and password are validated then changePassword() method is called*/
			boolean result=loginService.changePassword(userName,password);
			return result;
		}
	}
}
