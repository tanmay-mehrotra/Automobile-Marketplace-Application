package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.TO.CarTO;
import com.entity.Car;
import com.exception.CarNotAvailableException;
import com.exception.NoCarsBookedException;
import com.exception.NoRegisteredCarsException;
import com.exception.NoSuchCarException;
import com.exception.NoUnregsiteredCarsException;


public class CarService
{

	EntityManager em=null;


	
	@SuppressWarnings("unchecked")
	public String  getCar(int modelId,int colorId) throws Exception,CarNotAvailableException
	{   
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();   
		try
		{
			Query query=em.createNativeQuery("select sf_checkCarAvailability(?2,?1) from dual");
			query.setParameter(1,modelId);
			query.setParameter(2,colorId);

			List list=(List)query.getSingleResult();

			String str=(String)list.get(0);

			if(str.equals("I")||str.equals("N"))
				throw new CarNotAvailableException();
			else
				return str;
		}

		finally
		{
			em.close();
			emf.close();
		}  	

	}

	public boolean addCar(CarTO carTO)
	{   
		Car car=new Car();
		car.setChassisNo(carTO.getChassisNo());
		car.setCustId(carTO.getCustId());
		car.setColorId(carTO.getColorId());
		car.setModelId(carTO.getModelId());
		car.setDateOfDelivery(null);
		car.setRegistrationNo(null);
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		EntityManager em = null;
		try
		{   
			em=emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(car);
			em.getTransaction().commit();
			return true;
		}
		finally
		{
			if(em!=null){
				em.close();

			}
		}  	
	}
	@SuppressWarnings("unchecked")
	public List<CarTO> getUnregisteredCars()throws NoUnregsiteredCarsException 
	{

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		try
		{
			Query query =em.createQuery("select c from Car c where c.registrationNo is null");

			List<Car> list =query.getResultList();
			List<CarTO> carList=new ArrayList<CarTO>();

			if(list==null)
			{
				throw new NoUnregsiteredCarsException();
			}

			for(int count = 0; count < list.size();count++)
			{
				Car car=(Car)list.get(count);
				CarTO carTO=new CarTO();
				carTO.setChassisNo(car.getChassisNo());
				carTO.setColorId(car.getColorId());
				carTO.setCustId(car.getCustId());
				carTO.setDateOfDelivery(car.getDateOfDelivery());
				carTO.setModelId(car.getModelId());
				carTO.setRegistrationNo(car.getRegistrationNo());
				carList.add(carTO);

			}

			return carList;
		}
		finally
		{
			if ( em != null)
			{
				em.close();
			}
		}
	}

	public boolean deliverCar(String chassisNo) throws NoSuchCarException
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		EntityManager em = emf.createEntityManager();

		try
		{
			em.getTransaction().begin();
			Car car =em.find(Car.class,chassisNo);

			if(car==null)
			{
				throw new NoSuchCarException();
			}

			Query query =em.createQuery("UPDATE Car set dateOfDelivery=?1 where chassisNo=?2");
			Date date= new Date(); 
			query.setParameter(2,chassisNo);
			query.setParameter(1, date);

			int num=query.executeUpdate();
			if(num==0)
			{
				return false;
			}
			em.getTransaction().commit();
			return  true;

		}
		finally
		{
			if ( em != null)
			{
				em.close();
			}
		}
	}




	public String registerCar(String chassisNo) throws NoSuchCarException
	{

		EntityManagerFactory emf= Persistence.createEntityManagerFactory("Project");
		em=emf.createEntityManager();

		Random random = new Random();
		String num = random.nextInt() + "";
		try
		{
			em.getTransaction().begin();
			Car obj=em.find(Car.class,chassisNo);
			if(obj == null)
			{
				throw new NoSuchCarException();
			}
			String registrationNo = "KA 09 EW "+num.substring(1,4);
			obj.setRegistrationNo(registrationNo);
			em.getTransaction().commit();

			return registrationNo;
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
	public	List<CarTO> getUndeliveredCars() throws NoRegisteredCarsException
	{	
		EntityManagerFactory entity = Persistence.createEntityManagerFactory("Project");
		EntityManager em = entity.createEntityManager();

		try {
			Query query =em.createQuery("SELECT c FROM Car c WHERE c.registrationNo is not null");

			List<Car> list =query.getResultList();
			List<CarTO> carList=new ArrayList<CarTO>();

			if(list==null)
			{
				throw new NoRegisteredCarsException();
			}

			for(int count = 0; count < list.size();count++)
			{
				Car car=(Car)list.get(count);
				CarTO carTO=new CarTO();
				carTO.setChassisNo(car.getChassisNo());
				carTO.setColorId(car.getColorId());
				carTO.setCustId(car.getCustId());
				carTO.setDateOfDelivery(car.getDateOfDelivery());
				carTO.setModelId(car.getModelId());
				carTO.setRegistrationNo(car.getRegistrationNo());
				carList.add(carTO);
			}

			return carList;
		}
		finally
		{
			if ( em != null)
			{
				em.close();
			}
		}	
	}

	@SuppressWarnings("unchecked")
	public List<CarTO> getCustomerCar() throws NoCarsBookedException{

		//Creating EntityManager instance using EntityManagerFactory
		EntityManager em =null;
		try
		{   List<CarTO> listcarto=new ArrayList<CarTO>();

		/*Creating an instance of EntityManagerFactory to manage the entitymanager*/
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");

		/*Creating an instance of EntityManager to manage the entity*/
		em = emf.createEntityManager();

		/*Creating query */
		Query query=em.createQuery("select c from Car c");
		List<Car> list =query.getResultList();

		if(list.size()==0){
			throw new NoCarsBookedException();
		}			
		int counter=0;
		while(counter<list.size()){
			Car car = (Car)list.get(counter);
			CarTO carTO=new CarTO();

			carTO.setChassisNo(car.getChassisNo());
			carTO.setModelId(car.getModelId());
			carTO.setDateOfDelivery(car.getDateOfDelivery());
			carTO.setRegistrationNo(car.getRegistrationNo());
			carTO.setColorId(car.getColorId());
			carTO.setCustId(car.getCustId());

			listcarto.add(carTO);
			counter++;
		}
		return listcarto;
		}

		/*Closing the Connection*/
		finally{
			if (em != null) {
				em.close();
			}
		}

	}	

	@SuppressWarnings("unchecked")
	public CarTO getCar(String chassisNo) throws NoSuchCarException{
		//Creating EntityManager instance using EntityManagerFactory
		EntityManager em =null;
		try
		{   
			/*Creating an instance of EntityManagerFactory to manage the entitymanager*/
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");

			/*Creating an instance of EntityManager to manage the entity*/
			em = emf.createEntityManager();

			/*Creating query */
			Query query=em.createQuery("select c from Car c where c.chassisNo = ?1");
			/*Setting parameter for the query*/
			query.setParameter(1,chassisNo);

			List<Car> list = query.getResultList();

			if(list.size()==0){
				throw new NoSuchCarException();
			}	


			Car car=(Car)list.get(0);
			CarTO carto=new CarTO();
			carto.setChassisNo(car.getChassisNo());
			carto.setModelId(car.getModelId());
			carto.setDateOfDelivery(car.getDateOfDelivery());
			carto.setRegistrationNo(car.getRegistrationNo());
			carto.setColorId(car.getColorId());
			carto.setCustId(car.getCustId());		
			return carto;
		}
		/*Closing the Connection*/
		finally
		{
			if (em != null) {
				em.close();
			}
		}

	}


	@SuppressWarnings("unchecked")
	public List<CarTO> getCustomerCar(int customerId) throws NoCarsBookedException{
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em = emf.createEntityManager();
			List<CarTO> listCar=new ArrayList<CarTO>();

			Query query=em.createQuery("select c from Car c where c.custId=?1");
			query.setParameter(1,customerId);
			List list=query.getResultList();
			if(list.size()==0){
				throw new NoCarsBookedException();
			}
			for(int i=0;i<list.size();i++){
				Car car=(Car)list.get(i);
				CarTO carTo = new CarTO();
				carTo.setChassisNo(car.getChassisNo());
				carTo.setColorId(car.getColorId());
				carTo.setCustId(car.getCustId());
				carTo.setDateOfDelivery(car.getDateOfDelivery());
				carTo.setModelId(car.getModelId());
				carTo.setRegistrationNo(car.getRegistrationNo());
				listCar.add(carTo);
			}
			return listCar;
		}
		finally{
			if(em!=null){
				em.close();
			}
		}

	}



}
