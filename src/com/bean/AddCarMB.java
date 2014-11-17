package com.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import com.TO.CarTO;
import com.TO.ModelColorsTO;
import com.TO.ModelTO;
import com.exception.InvalidModelIdException;
import com.exception.NoModelsException;
import com.wrapper.AutoSellWrapper;



public class AddCarMB {

	private String chassisNo;
	private Integer modelId;
	private String message;
	private List<SelectItem> modelIdList;
	private List<SelectItem> colorIdList;
	private int colorId;

	public Integer getModelId() {
		return modelId;
	}

	public String getMessage() {
		return message;
	}
	public String getChassisNo() {
		return chassisNo;
	}
	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SelectItem> getModelIdList() {
		return modelIdList;
	}
	public void setModelIdList(List<SelectItem> modelIdList) {
		this.modelIdList = modelIdList;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public List<SelectItem> getColorIdList() {
		return colorIdList;
	}
	public void setColorIdList(List<SelectItem> colorIdList) {
		this.colorIdList = colorIdList;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public AddCarMB() {

		try {
			/*Populating modelId list using ModelTO transfer object*/
			this.colorIdList=new ArrayList<SelectItem>();
			this.modelIdList = new ArrayList<SelectItem>();
			List<ModelTO> list = new ArrayList<ModelTO>();

			/*Calling getModels method of service class to populate modelIdList*/
			list = new AutoSellWrapper().getModels();


			Iterator<ModelTO> iter =list.iterator();

			while(iter.hasNext()){

				ModelTO modelTO = iter.next();

				SelectItem item = new SelectItem(modelTO.getModelId(),modelTO.getModelName());
				modelIdList.add(item);
			}
		} catch (NoModelsException e) {
			/*Exception thrown if models not found*/
			this.setMessage(e.getMessage());

		}
	}
	public void getModelColor(ValueChangeEvent event){

		try{
			/*Trapping the value of modelId on change*/
			Integer modelId1=(Integer)event.getNewValue();

			/*populating colorIdList using ModelColorsTO transfer object*/
			List<ModelColorsTO> list=new ArrayList<ModelColorsTO>();
			list=new AutoSellWrapper().getModelColor(modelId1);

			Iterator<ModelColorsTO> it=list.iterator();
			while(it.hasNext()){
				ModelColorsTO modelColorTO=it.next();

				SelectItem item = new SelectItem(modelColorTO.getColorId(),modelColorTO.getColorsAvailable());

				this.colorIdList.add(item);

			}
		}
		catch(InvalidModelIdException e){
			/*Exception thrown when invalid model id is passed to server*/
			this.setMessage(e.getMessage());
		}


	}
	public String addCar(){
		/*This method will call the addCar() method of service class to add a new car to the database*/
		AutoSellWrapper wrapper=new AutoSellWrapper();
		/*Populating CarTo object which will be used to transfer data from the jsp page to the service class*/
		CarTO carTO=new CarTO();
		carTO.setChassisNo(this.getChassisNo());
		carTO.setColorId(this.getColorId());
		Date date= new Date();
		carTO.setDateOfDelivery(date);
		carTO.setModelId(this.getModelId());
		carTO.setRegistrationNo(null);

		boolean bol=wrapper.addCar(carTO);
		if(bol==true){
			/*If car successfully added addCar() method will return true*/
			return"success";
		}
		else{
			return "fail";
		}

	}

	public static boolean isChassisNo(String strAlphaNum) {
		boolean isChar=false;

		char[] charArray = strAlphaNum.toCharArray();
		if ((charArray[0]=='c'&&  charArray[1]=='h')){
			isChar=true;
		}
		return isChar;	
	}
	public void validateChassisNo(FacesContext facesContext, UIComponent component, Object object)throws ValidatorException
	{
		if(facesContext==null || component==null){
			throw new NullPointerException();
		}
		if(!(component instanceof UIInput)){
			return;
		}
		/*Instantiating FacesMessage*/
		FacesMessage message =new FacesMessage();
		message.setSummary("Chassis no should start with 'ch' ");
		String string=object.toString();
		boolean bool=isChassisNo(string);
		if(bool==false )
		{
			/*throwing ValidatorException if the	character is a number*/
			message.setDetail("Chassis no should start with 'ch' ");
			throw new ValidatorException(message);
		}

	}




}




