package com.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.TO.EnquiryTO;
import com.entity.Enquiry;
import com.exception.InvalidRequestIdException;

import com.exception.NoJobsAssignedException;


public class EnquiryService {
	EntityManager em =null;

	@SuppressWarnings("unchecked")
	
	public List<EnquiryTO> getFollowUpEnquiry(int employeeId) throws Exception,NoJobsAssignedException
	{   
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();   
		try
		{
			//get the Jobs Assigned
			Query query=em.createQuery("select k from Enquiry k where k.marketExecutiveId=?1 and k.status=?2");
			query.setParameter(1,employeeId);
			query.setParameter(2,'F');

			List<Enquiry> list = query.getResultList();

			if(list.size()==0)
				throw new NoJobsAssignedException();

			List<EnquiryTO> list1=new ArrayList<EnquiryTO>();
			for(int i=0;i<list.size();i++)
			{ 
				EnquiryTO enquiryto=new EnquiryTO();

				Enquiry enquiry=(Enquiry)list.get(i);

				enquiryto.setColorId(enquiry.getColorId());
				enquiryto.setDateOfRequest(enquiry.getDateOfRequest());
				enquiryto.setMarketExecutiveId(enquiry.getMarketExecutiveId());
				enquiryto.setModelId(enquiry.getModelId());
				enquiryto.setProspectiveCustomerId(enquiry.getProspectCustomerId());
				enquiryto.setRequestId(enquiry.getRequestId());
				enquiryto.setStatus(enquiry.getStatus());
				list1.add(enquiryto);

			}




			return list1;
		}
		finally
		{
			if(em!=null){
				em.close();
			}

		}   
	}
	/**
     * This method queries the database updates with the input values 
     * feeded to it. 
     * @param integer,char
     * @return boolean
     */
	public boolean updateEnquiryStatus(int requestId,char status) throws Exception,InvalidRequestIdException
	{   
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		try
		{

			Enquiry enquiry=em.find(Enquiry.class,requestId);
			if(enquiry==null)
				throw new InvalidRequestIdException();
			else
			{
				enquiry.setStatus(status);
				em.getTransaction().begin();
				em.merge(enquiry);
				em.getTransaction().commit();
				return true;
			}
		}
		finally
		{
			if(em!=null){
				em.close();
			}

		}  

	}
    
	/**
     * This method queries the database and fetches the full details of
     * a request Id passed to it. 
     * @param integer
     * @return Object
     */
	@SuppressWarnings("unchecked")
	public EnquiryTO getEnquiryDetails(Integer requestId) throws Exception,InvalidRequestIdException
	{   

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		try
		{

			Query query=em.createQuery("select k from Enquiry k where K.requestId=?1");
			query.setParameter(1,requestId);

			List<Enquiry> list = query.getResultList();
			if(list.size()==0){
				throw new InvalidRequestIdException();
			}	
			EnquiryTO enquiryto=new EnquiryTO();
			for(int i=0;i<list.size();i++)
			{   
			Enquiry enquiry=(Enquiry)list.get(i);
			enquiryto.setColorId(enquiry.getColorId());
			enquiryto.setDateOfRequest(enquiry.getDateOfRequest());
			enquiryto.setMarketExecutiveId(enquiry.getMarketExecutiveId());
			enquiryto.setModelId(enquiry.getModelId());
			enquiryto.setProspectiveCustomerId(enquiry.getProspectCustomerId());
			enquiryto.setRequestId(enquiry.getRequestId());
			enquiryto.setStatus(enquiry.getStatus());
			}
			return enquiryto;

		}
		finally
		{
			if(em!=null){
				em.close();
			}

		}  		
	}




	@SuppressWarnings("unchecked")
	public List<EnquiryTO> getNewEnquiry(Integer employeeId)throws NoJobsAssignedException{

		//Declaring and initializing references
		Enquiry enquiry=null;
		EntityManager em=null;
		List<EnquiryTO> listEnquiryTO=new ArrayList<EnquiryTO>();

		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			/*Creating a query to search that instance of Enquiry entity 
			which has some specific properties*/
			Query query=em.createQuery("Select e from Enquiry e where e.status=?1 and e.marketExecutiveId=?2");
			//Setting Query parameters
			query.setParameter(1, 'E');
			query.setParameter(2, employeeId);

			List rs=query.getResultList();

			//Checking if the resulting list of query is empty and throwing appropriate exceptions
			if(rs.size()==0)
				throw new NoJobsAssignedException();
			else
			{
				/*Loop for retreiving the elements of the list row wise and 
					setting enquiryTO object for returning*/
				for (int count = 0;count<rs.size();count++)
				{
					EnquiryTO enquiryTO=new EnquiryTO();
					enquiry=(Enquiry) rs.get(count);
					//Setting the enquiryTO object	
					enquiryTO.setColorId(enquiry.getColorId());
					enquiryTO.setDateOfRequest(enquiry.getDateOfRequest());
					enquiryTO.setMarketExecutiveId(enquiry.getMarketExecutiveId());
					enquiryTO.setModelId(enquiry.getModelId());
					enquiryTO.setProspectiveCustomerId(enquiry.getProspectCustomerId());
					enquiryTO.setRequestId(enquiry.getRequestId());
					enquiryTO.setStatus(enquiry.getStatus());
					listEnquiryTO.add(enquiryTO);

				}
				return listEnquiryTO;

			}
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}

	}

	public EnquiryTO getEnquiryDetails(int requestId)throws InvalidRequestIdException {

		//Declaring and initializing references
		EnquiryTO enquiryTO=new EnquiryTO();
		EntityManager em=null;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em=emf.createEntityManager();

		Enquiry enquiry=em.find(Enquiry.class, requestId);
		enquiryTO.setColorId(enquiry.getColorId());
		enquiryTO.setDateOfRequest(enquiry.getDateOfRequest());
		enquiryTO.setMarketExecutiveId(enquiry.getMarketExecutiveId());
		enquiryTO.setModelId(enquiry.getModelId());
		enquiryTO.setProspectiveCustomerId(enquiry.getProspectCustomerId());
		enquiryTO.setRequestId(enquiry.getRequestId());
		enquiryTO.setStatus(enquiry.getStatus());
		return enquiryTO;
	}

	public boolean addEnquiry(EnquiryTO enquiryTO){
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();
			/*Populating enquiry object with the values brought in by enquiryTO object*/
			Enquiry enquiry=new Enquiry();
			enquiry.setColorId(enquiryTO.getColorId());
			enquiry.setDateOfRequest(enquiryTO.getDateOfRequest());
			enquiry.setMarketExecutiveId(enquiryTO.getMarketExecutiveId());
			enquiry.setModelId(enquiryTO.getModelId());
			enquiry.setProspectCustomerId(enquiryTO.getProspectiveCustomerId());
			enquiry.setStatus(enquiryTO.getStatus());
			em.persist(enquiry);
			et.commit();

		}
		finally{
			em.close();
		}

		return true;

	}

	@SuppressWarnings("unchecked")
	public List<EnquiryTO> getNewEnquiry(int employeeId) throws NoJobsAssignedException{
		EntityManagerFactory entity = Persistence.createEntityManagerFactory("Project");
		EntityManager em = entity.createEntityManager();
		try{

			Query query=em.createQuery("select e from Enquiry e where e.marketExecutiveId=?1 and e.status='E'");

			query.setParameter(1, employeeId);
			List<EnquiryTO> list=new ArrayList<EnquiryTO>();
			List templist=query.getResultList();

			int size=templist.size();
			for(int i=0;i<size;i++){
				Enquiry enquiry=(Enquiry)templist.get(i);
				EnquiryTO enquiryTo=new EnquiryTO();
				enquiryTo.setRequestId(enquiry.getRequestId());
				enquiryTo.setProspectiveCustomerId(enquiry.getProspectCustomerId());
				enquiryTo.setColorId(enquiry.getColorId());
				enquiryTo.setDateOfRequest(enquiry.getDateOfRequest());
				enquiryTo.setMarketExecutiveId(enquiry.getMarketExecutiveId());
				enquiryTo.setModelId(enquiry.getModelId());
				enquiryTo.setStatus(enquiry.getStatus());
				list.add(enquiryTo);
			}
			if(list!=null){
				return list;
			}
			else{
				throw new NoJobsAssignedException();
			}
		}
		finally{
			if(em!=null){
				em.close();
			}
		}
	}


}





























