package com.manager;

import com.exception.InvalidUserException;
import com.service.CustomerService;
import com.TO.CustomerTO;

public class CustomerManager {
	public CustomerTO getCustomer(String userName)throws InvalidUserException
	{
		CustomerService customerService=new CustomerService();
		CustomerTO customerTO=customerService.getCustomer(userName);
		return customerTO;
	}

}
