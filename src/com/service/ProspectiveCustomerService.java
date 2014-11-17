package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.TO.ProspectiveCustomerTO;
import com.entity.ProspectiveCustomer;
import com.exception.InvalidProspectiveCustomerIdException;

public class ProspectiveCustomerService {
	EntityManager em=null;

	public ProspectiveCustomerTO getProspectiveCustomer(Integer prospectiveCustomerId) throws Exception,InvalidProspectiveCustomerIdException
	{    
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em = emf.createEntityManager();
			Query query=em.createQuery("select p from  ProspectiveCustomer p where p.prospectiveCustomerId=?1");
			query.setParameter(1,prospectiveCustomerId);
			if(query==null)
				throw new InvalidProspectiveCustomerIdException();

			ProspectiveCustomer prospectiveCustomer=(ProspectiveCustomer)query.getSingleResult();

			ProspectiveCustomerTO prospectiveCustomerTO=new ProspectiveCustomerTO();
			prospectiveCustomerTO.setCustomerName(prospectiveCustomer.getCustomerName());
			prospectiveCustomerTO.setEmailId(prospectiveCustomer.getEmailId());
			prospectiveCustomerTO.setPhoneNumber(prospectiveCustomer.getPhoneNo());
			prospectiveCustomerTO.setProspectCustomerId(prospectiveCustomer.getProspectiveCustomerId());


			return prospectiveCustomerTO;
		}
		finally
		{
			em.close();
		} 
	}

	public ProspectiveCustomerTO addProspectCustomer(ProspectiveCustomerTO prospectCustomerTO){
		EntityManager em=null;
		try{
			/*adding a new prospective customer to the database*/
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();

			et.begin();
			ProspectiveCustomer prospectCustomer=new ProspectiveCustomer();
			prospectCustomer.setCustomerName(prospectCustomerTO.getCustomerName());
			prospectCustomer.setEmailId(prospectCustomerTO.getEmailId());
			prospectCustomer.setPhoneNo(prospectCustomerTO.getPhoneNumber());
			em.persist(prospectCustomer);
			et.commit();
			prospectCustomerTO.setProspectCustomerId(prospectCustomer.getProspectiveCustomerId());

		}
		finally{
			em.close();
		}
		return prospectCustomerTO;

	}
}
