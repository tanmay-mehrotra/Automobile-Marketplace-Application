package com.service;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.TO.ModelTO;
import com.entity.Model;
import com.exception.InvalidModelIdException;
import com.exception.NoModelsException;

public class ModelService {

	EntityManager em=null;

	public ModelTO getModel(int modelId)throws Exception,InvalidModelIdException
	{   
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em = emf.createEntityManager();
		try
		{

			Query query=em.createQuery("select m from Model m where m.modelId=?1");
			query.setParameter(1,modelId);
			if(query==null)
				throw new InvalidModelIdException();

			Model list=(Model)query.getSingleResult();

			ModelTO modelTO=new ModelTO();
			modelTO.setCarType(list.getCarType());
			modelTO.setCubicCapacity(list.getCubicCapacity());
			modelTO.setFuelType(list.getFuelType());
			modelTO.setModelId(list.getModelId());
			modelTO.setModelName(list.getModelName());
			modelTO.setPrice(list.getPrice());
			modelTO.setStatus(list.getStatus());
			modelTO.setVariant(list.getVariant());



			return modelTO;
		}
		finally
		{
			em.close();
			emf.close();
		}   
	}
	public int addModel(ModelTO modelTO)
	{
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project"); 
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();

		et.begin();
		Model model = new Model();
		model.setCarType(modelTO.getCarType());
		model.setCubicCapacity(modelTO.getCubicCapacity());
		model.setFuelType(modelTO.getFuelType());
		model.setModelName(modelTO.getModelName());
		model.setPrice(modelTO.getPrice());
		model.setStatus('A');
		model.setVariant(modelTO.getVariant());
		em.persist(model);
		et.commit();
		return model.getModelId();

	}



	@SuppressWarnings("unchecked")
	public List<ModelTO> getModels() throws NoModelsException
	{
		EntityManager em=null;

		Model model;

		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			List<ModelTO> listModelTO=new ArrayList<ModelTO>();
			Query query=em.createQuery("Select m FROM Model m");
			List rs=query.getResultList();
			if(rs.size()==0)
				throw new NoModelsException();
			for(int count=0;count<rs.size();count++)
			{
				ModelTO modelTO=new ModelTO();
				model=(Model)rs.get(count);
				modelTO.setModelId(model.getModelId());
				modelTO.setModelName(model.getModelName());
				modelTO.setPrice(model.getPrice());
				modelTO.setCarType(model.getCarType());
				modelTO.setCubicCapacity(model.getCubicCapacity());
				modelTO.setFuelType(model.getFuelType());
				modelTO.setStatus(model.getStatus());
				modelTO.setVariant(model.getVariant());

				listModelTO.add(modelTO);

			}
			return listModelTO;
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
	public List<ModelTO> getModelByType(String carType) throws NoModelsException {

		EntityManager em=null;

		Model model;

		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			List<ModelTO> listModelTO=new ArrayList<ModelTO>();
			Query query=em.createQuery("Select m FROM Model m where m.carType=?1");
			query.setParameter(1, carType);
			List rs=query.getResultList();
			if(rs.size()==0)
				throw new NoModelsException();
			for(int count=0;count<rs.size();count++)
			{
				ModelTO modelTO=new ModelTO();
				model=(Model)rs.get(count);
				modelTO.setModelId(model.getModelId());
				modelTO.setModelName(model.getModelName());
				modelTO.setPrice(model.getPrice());
				modelTO.setCarType(model.getCarType());
				modelTO.setCubicCapacity(model.getCubicCapacity());
				modelTO.setFuelType(model.getFuelType());
				modelTO.setStatus(model.getStatus());
				modelTO.setVariant(model.getVariant());

				listModelTO.add(modelTO);

			}

			return listModelTO;
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
	public List<String> getDistinctCarType() throws NoModelsException {

		EntityManager em=null;

		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();
			List<String> listCarType=new ArrayList<String>();
			Query query=em.createQuery("Select m.carType FROM Model m");
			List rs=query.getResultList();
			if(rs.size()==0)
				throw new NoModelsException();
			for(int count=0;count<rs.size();count++)
			{
				int flag=0;
				String carType=(String)rs.get(count);				
				for (int count1 = 0;count1<listCarType.size();count1++)
				{
					if(((String)listCarType.get(count1)).equals(carType))
						flag=1;
				}
				if (flag!=1)
					listCarType.add(carType);

			}
			return listCarType;
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
	public List<ModelTO> getModelByPrice(double startPrice,double endPrice ) throws NoModelsException{
		EntityManager em=null;
		try
		{
			List<ModelTO> list=new ArrayList<ModelTO>();
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em = emf.createEntityManager();

			Query query=em.createQuery("select m from Model m where m.price between ?1 and ?2");
			query.setParameter(1,startPrice);
			query.setParameter(2,endPrice);
			List<Model> listModel = query.getResultList();
			if(listModel.size()==0 )
			{
				throw new NoModelsException();
			}	

			int i=0;
			while(i<listModel.size())
			{
				Model model = (Model)listModel.get(i);
				ModelTO modelTO = new ModelTO();

				modelTO.setModelId(model.getModelId());
				modelTO.setModelName(model.getModelName());
				modelTO.setPrice(model.getPrice());
				modelTO.setFuelType(model.getFuelType());
				modelTO.setCubicCapacity(model.getCubicCapacity());
				modelTO.setCarType(model.getCarType());
				modelTO.setVariant(model.getVariant());
				modelTO.setStatus(model.getStatus());
				list.add(modelTO);
				i++;
			}

			return list;
		}

		finally
		{
			em.close();
		}

	}


	public boolean withdrawModel(int modelId){
		EntityManager em=null;
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query=em.createNativeQuery("Delete from Model where modelId=?1 cascade constraint");
			query.setParameter(1,modelId);
			int count = query.executeUpdate();
			em.getTransaction().commit();
			if (count == 0)
				return false; 
			else
				return true; 

		}		
		finally
		{
			em.close();
		}

	}
	public boolean updateModel(int modelId,double price)throws InvalidModelIdException{
		EntityManager em=null;
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createNativeQuery("update Model SET price=?1 where modelId=?2");
			query.setParameter(1,price);
			query.setParameter(2,modelId);
			int count=query.executeUpdate();
			em.getTransaction().commit();
			if (count == 0)
				return false; 
			else
				return true; 

		}		
		finally
		{
			em.close();
		}

	}


	@SuppressWarnings("unchecked")
	public List<String> getDistinctModels() throws NoModelsException{

		//Creating EntityManager instance using EntityManagerFactory
		EntityManager em =null;
		try
		{
			/*Creating an instance of EntityManagerFactory to manage the entitymanager*/
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");

			/*Creating an instance of EntityManager to manage the entity*/
			em = emf.createEntityManager();

			/*Creating Query*/
			Query query=em.createQuery("select distinct d.modelName from Model d");

			List<String> list = query.getResultList();

			if(list.size()==0){
				throw new NoModelsException();
			}			
			return list;
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

	@SuppressWarnings("unchecked")
	public List<ModelTO> getModelByName(String modelName) throws NoModelsException{

		//Creating EntityManager instance using EntityManagerFactory
		EntityManager em =null;

		try{   

			List<ModelTO> list1=new ArrayList<ModelTO>();

			/*Creating an instance of EntityManagerFactory to manage the entitymanager*/
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");

			/*Creating an instance of EntityManager to manage the entity*/
			em = emf.createEntityManager();

			/*Creating Query*/
			Query query=em.createQuery("select a from Model a where a.modelName=?1");

			/*Setting Parameter*/
			query.setParameter(1,modelName);

			List<Model> list = query.getResultList();

			if(list.size()==0){
				throw new NoModelsException();
			}

			int counter=0;
			while(counter<list.size()){
				Model model = (Model)list.get(counter);
				ModelTO modelTO=new ModelTO();

				modelTO.setCubicCapacity(model.getCubicCapacity());
				modelTO.setCarType(model.getCarType());
				modelTO.setVariant(model.getVariant());
				modelTO.setStatus(model.getStatus());
				modelTO.setPrice(model.getPrice());
				modelTO.setFuelType(model.getFuelType());
				modelTO.setModelId(model.getModelId());
				modelTO.setModelName(model.getModelName());

				list1.add(modelTO);
				counter++;
			}
			return list1;
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



	@SuppressWarnings("unchecked")
	public List<Integer> viewModelId() throws NoModelsException{
		EntityManager em=null;
		try
		{
			/*populating the list with model Id*/
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em = emf.createEntityManager();

			Query query=em.createQuery("select x.modelId from Model x");

			List<Integer> list = query.getResultList();
			if(list.size()==0){
				/*Exception thrown if no model id found*/

				throw new NoModelsException();
			}	

			return list;
		}
		finally
		{
			em.close();
		}

	}









}
