package com.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.TO.ModelColorsTO;
import com.entity.ModelColors;
import com.exception.InvalidModelIdException;

public class ModelColorService {
	@SuppressWarnings("unchecked")
	public List<ModelColorsTO> getModelColor(int modelId) throws InvalidModelIdException{
		EntityManager em=null;
		try
		{
			/*Retrieving the list of model colors corr to a given model id*/
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em = emf.createEntityManager();
			List<ModelColorsTO> list1 = new ArrayList<ModelColorsTO>();
			Query query=em.createQuery("select x from ModelColors x where x.modelId=?1");
			query.setParameter(1,modelId);
			List<ModelColors> list = query.getResultList();
			Iterator<ModelColors> iter =list.iterator();


			while(iter.hasNext()){
				/*Populating modelcolorTO object with values returned from the query*/
				ModelColors modelColor = iter.next();
				ModelColorsTO modelColorTO=new ModelColorsTO();
				modelColorTO.setColorId(modelColor.getColorId());
				modelColorTO.setColorsAvailable(modelColor.getColorsAvailable());
				modelColorTO.setModelId(modelColor.getModelId());
				list1.add(modelColorTO);
			}

			if(list.size()==0){
				/*Exception thrown if model id is invalid*/
				throw new InvalidModelIdException();
			}			
			return list1;
		}
		finally
		{
			em.close();
		}

	}

}
