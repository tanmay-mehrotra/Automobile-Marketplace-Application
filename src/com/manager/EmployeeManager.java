package com.manager;


import com.exception.NoEmployeeFoundException;
import com.service.EmployeeService;
import com.TO.EmployeeTO;

public class EmployeeManager {
	public EmployeeTO getEmployee(String userName)throws NoEmployeeFoundException
	{
		EmployeeService employeeService=new EmployeeService();
		EmployeeTO employeeTO=employeeService.getEmployee(userName);
		return employeeTO;
	}

}