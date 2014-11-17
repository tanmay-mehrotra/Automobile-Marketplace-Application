package com.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.TO.ModelTO;
import com.exception.NoModelsException;
import com.wrapper.AutoSellWrapper;

public class UpdateModelMB {

	private Integer modelId;
	private Double price;
	private String message;
	private List<ModelTO>modelList;
	private ModelTO modelTO;
	private List<SelectItem>modelIdList;

	//Getters and Setters for the instance variables
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ModelTO> getModelList() {
		return modelList;
	}
	public void setModelList(List<ModelTO> modelList) {
		this.modelList = modelList;
	}
	public ModelTO getModelTO() {
		return modelTO;
	}
	public void setModelTO(ModelTO modelTO) {
		this.modelTO = modelTO;
	}
	public List<SelectItem> getModelIdList() {
		return modelIdList;
	}
	public void setModelIdList(List<SelectItem> modelIdList) {
		this.modelIdList = modelIdList;
	}

	//Constructor for populating the respective list at startup
	public UpdateModelMB() {
		this.modelIdList=new ArrayList<SelectItem>();
		AutoSellWrapper autoSellWrapper = new AutoSellWrapper();
		try
		{
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

	public String updateModel(){
		//boolean bool = true;
		AutoSellWrapper autoSellWrapper = new AutoSellWrapper();
		try{

			autoSellWrapper.updateModel(this.modelId,this.price);
		}
		//Catching Exceptions
		catch(Exception e)
		{
			this.message=e.getMessage();
			return "fail";
		}
		this.message="Model Updated Successfully";
		return "success";
	}

	public void getModel(ValueChangeEvent event){
		String id = (String)event.getNewValue();
		this.modelId=Integer.parseInt(id);
		this.message=null;
		AutoSellWrapper autoSellWrapper = new AutoSellWrapper();
		try {
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

