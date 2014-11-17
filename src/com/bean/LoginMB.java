package com.bean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.TO.CustomerTO;
import com.TO.EmployeeTO;
import com.TO.LoginTO;
import com.wrapper.AutoSellWrapper;

public class LoginMB {
	private String userName;
	private String password;
	private String message;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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

	public String validatePassword()
	{
		AutoSellWrapper autoSellWrapper = new AutoSellWrapper();

		try {

			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext etx = ctx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);
			LoginTO loginTO = autoSellWrapper.validatePassword(this.userName, this.password);
			session.setAttribute("role",loginTO.getRole());
			char role = loginTO.getRole();

			if(role == 'D' ||role == 'E')
			{
				EmployeeTO employeeTO = autoSellWrapper.getEmployee(this.userName);
				session.setAttribute("user", employeeTO.getEmpId());

			}
			else
			{				

				CustomerTO customerTO = autoSellWrapper.getCustomer(this.userName);
				session.setAttribute("user", customerTO.getCustomerId());								
			}
			return "success";

		} 
		catch (Exception e) 
		{
			this.setMessage(e.getMessage());
			return "failure";
		}



	}

	public String logout(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(false);
		session.invalidate();
		return "logout";
	}



}
