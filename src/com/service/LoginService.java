package com.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.TO.LoginTO;
import com.entity.Login;
import com.exception.InvalidUserException;

public class LoginService{
	EntityManager em=null;

	public   LoginTO addUser(LoginTO loginTO)
	{   
		Login login=new Login();
		login.setPassWord(loginTO.getPassWord());
		login.setRole(loginTO.getRole());
		login.setUserId(loginTO.getUserId());

		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(login);
		em.getTransaction().commit();
		return loginTO;
	}
	@SuppressWarnings("unchecked")
	public LoginTO validatePassword(String userName, String password) throws InvalidUserException
	{
		EntityManagerFactory emf= Persistence.createEntityManagerFactory("Project");
		EntityManager em=emf.createEntityManager();
		LoginTO loginTo = new LoginTO();
		try
		{
			em.getTransaction().begin();
			Query q=em.createNativeQuery("select Sf_validateUser(?1,?2) from dual");		
			q.setParameter(1,userName);
			q.setParameter(2,password);
			List<String> resultList=(List<String>)q.getSingleResult();
			String result = (String) resultList.get(0);
			char role = result.charAt(0);
			if(role == 'N')
			{
				throw new InvalidUserException("Invalid userId/Password");
			}
			else
			{
				loginTo.setRole(role);
			}
			return loginTo;
		}
		finally
		{
			//closing the entity manager
			if(em!=null)
			{
				em.close();
			}
		}

	}



	public boolean changePassword(String userName,String password) throws InvalidUserException{
		EntityManager em=null;


		try{
			/*After validation of userId and password the request is sent to this method for changing the password*/
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();
			/*Password is updated to the new password entered*/
			Login login=em.find(Login.class,userName);
			login.setPassWord(password);
			em.persist(login);
			et.commit();

			if(login==null){
				/*Exception thrown if user is invalid*/
				throw new InvalidUserException();
			}
			return true;



		}
		finally{
			em.close();

		}



	}

}
