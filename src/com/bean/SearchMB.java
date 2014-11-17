package com.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.TO.ModelTO;
import com.entity.Model;


import com.wrapper.AutoSellWrapper;

public class SearchMB {
	private char criteria;
	private List<SelectItem> criteriaList=new ArrayList<SelectItem>();
	private List<ModelTO> modelList;
	private List<SelectItem> modelIdList=new ArrayList<SelectItem>();
	private int modelId;
	private String modelName;
	private String message;
	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public char getCriteria() {
		return criteria;
	}

	public void setCriteria(char criteria) {
		this.criteria = criteria;
	}

	public List<SelectItem> getCriteriaList() {
		return criteriaList;
	}

	public void setCriteriaList(List<SelectItem> criteriaList) {
		this.criteriaList = criteriaList;
	}

	public List<ModelTO> getModelList() {
		return modelList;
	}

	public void setModelList(List<ModelTO> modelList) {
		this.modelList = modelList;
	}


	public List<SelectItem> getModelIdList() {
		return modelIdList;
	}

	public void setModelIdList(List<SelectItem> modelIdList) {
		this.modelIdList = modelIdList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void searchCriteria(ValueChangeEvent event){
		this.criteriaList=new ArrayList<SelectItem>();
		this.message=null;
		this.criteriaList.clear();
		this.modelIdList.clear();
		this.modelName=null;
		this.criteria=(Character) event.getNewValue();
		AutoSellWrapper autoSellWrapper=new AutoSellWrapper();
		List<String> list=null;
		try
		{
			if(this.criteria=='m'){

				list=autoSellWrapper.getDistinctModels();
				if(list.size()==0){
					this.setMessage("No models available.");
				}
				for(int i=0;i<list.size();i++){

					this.criteriaList.add(new SelectItem(list.get(i)));
				}

			}


			if(this.criteria=='t')
			{

				AutoSellWrapper wrapper=new AutoSellWrapper();
				List<String> listCarType=new ArrayList<String>();
				listCarType=wrapper.getDistinctCarType();
				Iterator<String> iterator = listCarType.iterator();
				while(iterator.hasNext())
				{
					String carType= iterator.next();
					SelectItem selectItem = new SelectItem(carType);
					this.criteriaList.add(selectItem);

				}
			}
			if(this.criteria=='p')
			{
				this.criteriaList.add(new SelectItem('1',"Less Than 2 Lakh"));
				this.criteriaList.add(new SelectItem('2',"2 Lakhs To 4 Lakh"));
				this.criteriaList.add(new SelectItem("3","4 Lakhs To 6 Lakh"));
				this.criteriaList.add(new SelectItem("4","6 Lakhs To 8 Lakh"));
				this.criteriaList.add(new SelectItem("5","Above 8 Lakh"));
			}

		}
		catch(Exception e){
			this.setMessage(e.getMessage());
		}

	}


	public void searchCars(ValueChangeEvent event){

		this.modelIdList=new ArrayList<SelectItem>();
		this.message=null;
		this.modelIdList.clear();

		try{

			if(this.criteria=='t')
			{

				String carType=(String) event.getNewValue();
				AutoSellWrapper wrapper=new AutoSellWrapper();
				this.modelList=wrapper.getModelByType(carType);
				Iterator<ModelTO> iterator = this.modelList.iterator();

				while(iterator.hasNext())

				{

					ModelTO modelTO = iterator.next();
					SelectItem selectItem = new SelectItem(modelTO.getModelId(),modelTO.getModelName());
					this.modelIdList.add(selectItem);

				}
			}
			if(this.criteria=='p')
			{

				String priceRange=(String) event.getNewValue();
				AutoSellWrapper wrapper = new AutoSellWrapper();
				if(priceRange.equals("1")){
					this.modelList=wrapper.getModelByPrice(0,200000);
				}
				else if(priceRange.equals("2")){
					this.modelList=wrapper.getModelByPrice(200001,400000);

				}
				else if(priceRange.equals("3")){
					this.modelList=wrapper.getModelByPrice(400001,600000);

				}
				else if(priceRange.equals("4")){
					this.modelList=wrapper.getModelByPrice(600001,800000);

				}
				else{
					this.modelList=wrapper.getModelByPrice(800001,5000000);

				}
				Iterator<ModelTO> model = this.modelList.iterator();

				while(model.hasNext())
				{
					ModelTO iter = model.next();
					SelectItem model2 = new SelectItem(iter.getModelId(),iter.getModelName());
					this.modelIdList.add(model2);
				}
			}
			if(this.criteria=='m')
			{
				this.modelName=(String)event.getNewValue();
				AutoSellWrapper autoSellWrapper=new AutoSellWrapper();
				this.modelList=autoSellWrapper.getModelByName(this.modelName);
				if(this.modelList.size()==0){
					this.setMessage("No variations available.");
				}
				for(int i=0;i<this.modelList.size();i++){

					String variant=this.modelList.get(i).getModelName()+this.modelList.get(i).getVariant();
					this.modelIdList.add(new SelectItem(this.modelList.get(i).getModelId(),variant));
				}


			}
		}
		catch(Exception e){
			this.setMessage(e.getMessage());
		}
	}



	public String raiseEnquiry()  { 

		try{

			Model model = new Model();
			model.setModelId(this.modelId);

			/* Following code is for session tracking, refer to JSF
				Day5 labguide extra assignments for details */
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext etx = ctx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);

			/* Setting two session variables. User for storing username
				and role for storing role of the user*/
			session.setAttribute("modelId", this.modelId);
			return "success";
		}
		//Catching Exceptions
		catch (Exception e){
			this.setMessage("Invalid Request");
			return "fail";
		}

	}
}