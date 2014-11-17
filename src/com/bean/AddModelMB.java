package com.bean;

import com.TO.ModelTO;
import com.wrapper.AutoSellWrapper;


public class AddModelMB {


	private int modelId;
	private String modelName;
	private Double price;
	private String fuelType;
	private Integer cubicCapacity;
	private String carType;
	private String variant;
	private char status;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Integer getCubicCapacity() {
		return cubicCapacity;
	}
	public void setCubicCapacity(Integer cubicCapacity) {
		this.cubicCapacity = cubicCapacity;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String addModel(){

		//Adding a new Model
		ModelTO modelTo=new ModelTO();
		AutoSellWrapper autoSellWrapper=new AutoSellWrapper();
		//Populating modelTo object.
		modelTo.setCarType(this.carType);
		modelTo.setCubicCapacity(this.cubicCapacity);
		modelTo.setFuelType(this.fuelType);
		modelTo.setModelName(this.modelName);
		modelTo.setPrice(this.price);
		modelTo.setStatus(this.status);
		modelTo.setVariant(this.variant);
		try{
			this.modelId=autoSellWrapper.addModel(modelTo);
			// returns modelId of added model
			this.setMessage("Model added successfully. Model ID: "+this.modelId);
			return "success";
		}catch(Exception e){
			this.setMessage(e.getMessage());
			return "fail";
		}

	}


}
