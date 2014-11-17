package com.manager;


import java.util.List;
import com.TO.ModelTO;
import com.exception.InvalidModelIdException;
import com.exception.NoModelsException;
import com.service.ModelService;

public class ModelManager {

	public List<ModelTO> getModels() throws NoModelsException 
	{	
		/*Instantiating Object Of Model Service class
		  and calling appropriate method*/	
		ModelService modelService=new ModelService();
		return modelService.getModels();

	}

	public List<ModelTO> getModelByType(String carType) throws NoModelsException 
	{
		/*Instantiating Object Of Model Service class
		  and calling appropriate method*/
		ModelService modelService=new ModelService();
		return modelService.getModelByType(carType) ;
	}

	public List<String> getDistinctCarType() throws NoModelsException 
	{
		/*Instantiating Object Of Model Service class
		  and calling appropriate method*/
		ModelService modelService=new ModelService();
		return modelService.getDistinctCarType() ;
	}

	public List<ModelTO> getModelByPrice(double startPrice,double endPrice) throws NoModelsException
	{
		List<ModelTO> list = null;
		/*Instantiating Object Of Model Service class
		  and calling appropriate method*/
		ModelService modelservice = new ModelService();
		list=modelservice.getModelByPrice(startPrice, endPrice);	
		return list ;
	}

	public ModelTO getModel(int modelId)throws Exception
	{
		/*Instantiating Object Of Model Service and ModelTO classes
		  and calling appropriate method*/
		ModelTO modelTO=new ModelTO();
		ModelService modelservice = new ModelService();
		modelTO = modelservice.getModel(modelId);
		return modelTO;
	}

	public boolean withdrawModel(int modelId)
	{
		/*Instantiating Object Of Model Service and ModelTO classes
		  and calling appropriate method*/
		ModelService modelservice = new ModelService();
		modelservice.withdrawModel(modelId);
		return true;
	}

	public boolean updateModel(int modelId,double price)throws InvalidModelIdException
	{
		/*Instantiating Object Of Model Service and ModelTO classes
		  and calling appropriate method*/
		ModelService modelservice = new ModelService();
		modelservice.updateModel(modelId,price);
		return true;
	}

	public int addModel(ModelTO modelTO)
	{	
		/*Instantiating Object Of Model Service and ModelTO classes
		  and calling appropriate method*/
		ModelService service = new ModelService();
		int modId= service.addModel(modelTO);
		return modId;

	}

	public List<String> getDistinctModels() throws NoModelsException
	{	
		/*Instantiating Object Of Model Service and ModelTO classes
		  and calling appropriate method*/
		ModelService service = new ModelService();
		List<String> list = service.getDistinctModels();	
		return list; 

	}

	public List<ModelTO> getModelByName(String modelName) throws NoModelsException
	{
		/*Instantiating Object Of Model Service and ModelTO classes
		  and calling appropriate method*/
		ModelService service = new ModelService();
		List<ModelTO> list = service.getModelByName(modelName);	
		return list; 

	}

}
