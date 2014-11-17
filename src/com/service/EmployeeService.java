package com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.TO.EmployeeTO;
import com.entity.Employee;
import com.entity.LoadManagement;
import com.exception.NoEmployeeFoundException;
/**
 * this service class provides method related to Quering Employee database.
 * @author Group 12
 */
public class EmployeeService
{
	@SuppressWarnings("unchecked")
	public EmployeeTO getEmployee (String userName) throws NoEmployeeFoundException
	{
		EmployeeTO employeeTO= new EmployeeTO();

		EntityManagerFactory emf= Persistence.createEntityManagerFactory("Project");
		EntityManager em=emf.createEntityManager();

		try
		{
			Query query=em.createQuery("SELECT C FROM Employee C WHERE C.userId =?1");
			query.setParameter(1, userName);
			List list=query.getResultList();
			if(list == null)
			{
				throw new NoEmployeeFoundException();
			}
			Employee employee = (Employee) list.get(0);
			employeeTO.setEmpId(employee.getEmpID());
			employeeTO.setEmpName(employee.getEmpName());
			employeeTO.setPhoneNumber(employee.getPhoneNumber());
			employeeTO.setRole(employee.getRole());
			employeeTO.setUserId(employee.getUserId());

			return employeeTO;	
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}
	@SuppressWarnings("unchecked")
	public EmployeeTO getMarketingExecutive() throws NoEmployeeFoundException {
		EntityManager em=null;
		EmployeeTO employeeTO=new EmployeeTO();
		try{
			int empId1=0;
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			/*Calling the stored function to retrieve the marketing executive id who is assigned minimum no of jobs*/
			Query q= em.createNativeQuery("select sf_findMarketingExecutive from dual");

			List q2=(List)q.getSingleResult();
			String empId = (String)q2.get(0);
			if(!(empId.equals("E")))
			{
				/*If the stored function returns a valid employee Id then it should be changed to integer*/

				empId1=Integer.parseInt(empId);
			}

			else{
				/*If no marketing executive id found then stored function will throw an exception*/

				throw new NoEmployeeFoundException();
			}			
			/*Populating employeeTO object with details retrieved from employee table corr to given employeeId */
			Employee employee=em.find(Employee.class, empId1);
			employeeTO.setEmpId(employee.getEmpID());
			employeeTO.setEmpName(employee.getEmpName());
			employeeTO.setPhoneNumber(employee.getPhoneNumber());
			employeeTO.setRole(employee.getRole());
			employeeTO.setUserId(employee.getUserId());



			em.getTransaction().begin();
			/*Updating no of jobs corr to the marketing executive id assigned the job*/
			LoadManagement loadManagement=em.find(LoadManagement.class,empId1);
			loadManagement.setNoOfJobs(loadManagement.getNoOfJobs()+1);
			em.merge(loadManagement);
			em.getTransaction().commit();
			return employeeTO ;

		}
		finally{
			em.close();
		}


	}
}

