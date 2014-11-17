package com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.TO.CustomerTO;
import com.entity.Customer;
import com.exception.InvalidUserException;

public class CustomerService {
	EntityManager em=null;

	public int addCustomer(CustomerTO customerTO) 
	{    

		Customer customer=new Customer();
		customer.setCustomerId(customerTO.getCustomerId());
		customer.setEmailId(customerTO.getEmailId());
		customer.setName(customerTO.getName());
		customer.setPhoneNumber(customerTO.getPhoneNumber());
		customer.setProspectCustomerId(customerTO.getProspectCustomerId());
		customer.setUserId(customerTO.getUserId());

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(customer);
		em.getTransaction().commit();
		return customer.getCustomerId();	
	}

	@SuppressWarnings("unchecked")
	public CustomerTO getCustomer(String userName) throws InvalidUserException
	{
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("Project");
		EntityManager em=emf.createEntityManager();
		CustomerTO customerTO=new CustomerTO();
		try{
			Query query=em.createQuery("select c from Customer c where c.userId =?1");
			query.setParameter(1,userName);
			List list=query.getResultList();
			if(list.isEmpty())
			{
				throw new InvalidUserException();
			}
			Customer cust = (Customer) list.get(0);

			customerTO.setCustomerId(cust.getCustomerId());
			customerTO.setEmailId(cust.getEmailId());
			customerTO.setName(cust.getName());
			customerTO.setPhoneNumber(cust.getPhoneNumber());
			customerTO.setProspectCustomerId(cust.getProspectCustomerId());
			customerTO.setUserId(cust.getUserId());
			return customerTO;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}

	}
}
