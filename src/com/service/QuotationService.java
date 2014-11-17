package com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.TO.QuotationTO;
import com.entity.Enquiry;
import com.entity.Quotation;
import com.exception.InvalidOfferIdException;
import com.exception.InvalidQuotationIdException;
import com.exception.InvalidRequestIdException;

public class QuotationService {
	EntityManager em=null;

	@SuppressWarnings("unchecked")
	public QuotationTO getQuotation(Integer requestId)throws Exception,InvalidQuotationIdException
	{
		try
		{  

			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em = emf.createEntityManager();
			Query query=em.createQuery("select q from Quotation q where q.requestId=?1 and q.dateGenerated=(select max(k.dateGenerated) from Quotation k where k.requestId=?1)");
			query.setParameter(1,requestId);

			if(query==null)
				throw new InvalidQuotationIdException();
			List list=query.getResultList();


			QuotationTO quotationTO= new QuotationTO();

			for(int i=0;i<list.size();i++)
			{
				Quotation quotation=(Quotation)list.get(i);

				quotationTO.setActualPrice(quotation.getActualPrice());
				quotationTO.setDateGenerated(quotation.getDateGenerated());
				quotationTO.setOfferId(quotation.getOfferId());
				quotationTO.setQuotationId(quotation.getQuotationId());
				quotationTO.setQuotedPrice(quotation.getQuotedPrice());
				quotationTO.setRequestId(quotation.getRequestId());
			}

			return quotationTO;
		}
		finally
		{
			em.close();
		}   
	}
	public QuotationTO updateQuotationStatus(int quotationId,char bookingStatus) throws Exception,InvalidQuotationIdException
	{  

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		try
		{  
			Quotation quotation=em.find(Quotation.class,quotationId);
			if(quotation==null)
				throw new InvalidQuotationIdException();
			quotation.setStatus(bookingStatus);
			em.getTransaction().begin();
			em.merge(quotation);
			em.getTransaction().commit();

			QuotationTO quotationTO=new QuotationTO();
			quotationTO.setActualPrice(quotation.getActualPrice());
			quotationTO.setDateGenerated(quotation.getDateGenerated());
			quotationTO.setOfferId(quotation.getOfferId());
			quotationTO.setQuotationId(quotation.getQuotationId());
			quotationTO.setQuotedPrice(quotation.getQuotedPrice());
			quotationTO.setRequestId(quotation.getRequestId());
			quotationTO.setStatus(quotation.getStatus());
			return quotationTO;
		}
		finally
		{
			em.close();
		}   


	}

	public QuotationTO getQuotationById(int quotationId)throws Exception,InvalidQuotationIdException
	{   
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		Quotation quotation=null;
		try
		{  
			Query query=em.createQuery("select k from Quotation k where k.quotationId=?1");
			query.setParameter(1,quotationId);
			if(query==null)
				throw new InvalidQuotationIdException();

			quotation=(Quotation)query.getSingleResult();
			QuotationTO quotationTO=new QuotationTO();

			quotationTO.setActualPrice(quotation.getActualPrice());
			quotationTO.setDateGenerated(quotation.getDateGenerated());
			quotationTO.setOfferId(quotation.getOfferId());
			quotationTO.setQuotationId(quotation.getQuotationId());
			quotationTO.setQuotedPrice(quotation.getQuotedPrice());
			quotationTO.setRequestId(quotation.getRequestId());
			quotationTO.setStatus(quotation.getStatus());
			return quotationTO;	    
		}
		finally
		{
			em.close();
		}   

	}
	public QuotationTO generateQuotation(QuotationTO quotationTO)throws InvalidRequestIdException,InvalidOfferIdException,Exception {

		EntityManager em=null;

		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			Quotation quotation=new Quotation();

			//Setting the quotation object for persistence
			quotation.setActualPrice(quotationTO.getActualPrice());
			quotation.setDateGenerated(quotationTO.getDateGenerated());
			quotation.setOfferId(quotationTO.getOfferId());
			quotation.setQuotedPrice(quotationTO.getQuotedPrice());
			quotation.setRequestId(quotationTO.getRequestId());
			quotation.setStatus(quotationTO.getStatus());
			et.begin();
			//persisting the quotation object
			em.persist(quotation);
			et.commit();
			/*retriving the quotationId from quotation object and 
				setting it to quotationTO object*/
			quotationTO.setQuotationId(quotation.getQuotationId());
			et=em.getTransaction();
			et.begin();
			Enquiry enquiry=em.find(Enquiry.class,quotationTO.getRequestId());
			//Checking if the Enquiry table does not contain the element
			if (enquiry!=null)
				enquiry.setStatus('F');
			et.commit();
			return quotationTO;
		}
		finally
		{
			if (em!=null)
				em.close();

		}
	}
	public QuotationTO updateQuotation(QuotationTO quotationTO)throws InvalidQuotationIdException
	{
		//Creating EntityManager instance using EntityManagerFactory
		EntityManager em =null;
		try
		{

			/*Creating an instance of EntityManagerFactory to manage the entitymanager*/
			EntityManagerFactory entity = Persistence.createEntityManagerFactory("Project");

			/*Creating an instance of EntityManager to manage the entity*/
			em = entity.createEntityManager();

			/*Transaction begins here */
			EntityTransaction et=em.getTransaction();

			/*Starting the transaction*/
			et.begin();

			/*Using find method and retrieve the Quotation class object for the given Quotation Id*/ 
			Quotation quotation=em.find(Quotation.class,quotationTO.getQuotationId());

			/*If the quotation object is null then throw Invalid QuotationId Exception*/
			if(quotation==null)
			{
				throw new InvalidQuotationIdException();

			}

			quotation.setOfferId(quotationTO.getOfferId());
			quotation.setQuotedPrice(quotationTO.getQuotedPrice());

			/*Mergeing the Transaction*/
			em.merge(quotation);
			/*Committing the Transaction*/
			et.commit();

			/*Creating an instance of quotation class and setting the paramenter*/

			QuotationTO quotationTO2=new QuotationTO();
			quotationTO2.setActualPrice(quotation.getActualPrice());
			quotationTO2.setDateGenerated(quotation.getDateGenerated());
			quotationTO2.setOfferId(quotation.getOfferId());
			quotationTO2.setQuotationId(quotation.getQuotationId());
			quotationTO2.setQuotedPrice(quotation.getQuotedPrice());
			quotationTO2.setRequestId(quotation.getRequestId());
			quotationTO2.setStatus(quotation.getStatus());
			return quotationTO2;

		}
		/*Closing the Connection*/
		finally
		{   
			if(em!=null)
			{
				em.close();
			}
		}

	}





}



