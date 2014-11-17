package com.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.TO.ModelTO;
import com.exception.NoModelsException;
import com.wrapper.AutoSellWrapper;

public class WithdrawModelMB
{

	private Integer modelId;
	private String message;
	private List<ModelTO>modelList;
	private ModelTO modelTO;
	private List<SelectItem>modelIdList;

	//Getters and Setters for the instance variables
	public Integer getModelId()
	{
		return modelId;
	}
	public void setModelId(Integer modelId) 
	{
		this.modelId = modelId;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	public List<ModelTO> getModelList()
	{
		return modelList;
	}
	public void setModelList(List<ModelTO> modelList)
	{
		this.modelList = modelList;
	}
	public ModelTO getModelTO()
	{
		return modelTO;
	}
	public void setModelTO(ModelTO modelTO) 
	{
		this.modelTO = modelTO;
	}
	public List<SelectItem> getModelIdList()
	{
		return modelIdList;
	}
	public void setModelIdList(List<SelectItem> modelIdList)
	{
		this.modelIdList = modelIdList;
	}

	//Constructor for populating the respective list at startup
	public WithdrawModelMB()
	{
		this.modelIdList=new ArrayList<SelectItem>();
		AutoSellWrapper autoSellWrapper = new AutoSellWrapper();
		try{
			this.modelList=autoSellWrapper.getModels();
			Iterator<ModelTO> model = this.modelList.iterator();

			while(model.hasNext())
			{
				ModelTO iter = model.next();
				SelectItem model2 = new SelectItem(iter.getModelId(),iter.getModelName()+" "+iter.getVariant());
				this.modelIdList.add(model2);
			}
		}
		//Catching Exceptions
		catch(NoModelsException e)
		{
			this.message=e.getMessage();
		}

	}
	//Function which withdraws a model and transfers control to next JSP
	public String withdrawModel()
	{

		AutoSellWrapper autoSellWrapper = new AutoSellWrapper();
		try{
			autoSellWrapper.withdrawModel(this.modelId);
		}
		//Catching Exceptions
		catch(Exception e)
		{
			this.message=e.getMessage();
			return "fail";
		}
		this.message="Model Withdrawn Successfully!!!!";
		return "success";

	}

	/*Value Change Event parametered function to display models 
		according to the category selected*/
	public void getModel(ValueChangeEvent event)
	{
		Integer id = (Integer)event.getNewValue();
		this.modelId=id;
		AutoSellWrapper autoSellWrapper = new AutoSellWrapper();
		try 
		{
			this.modelTO=autoSellWrapper.getModel(this.modelId);
		}
		//Catching Exceptions
		catch (Exception e)
		{
			e.printStackTrace();
			this.message=e.getMessage();
		}
	}
}

