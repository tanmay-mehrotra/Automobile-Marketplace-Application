package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ModelColors {
	@Id
	private int colorId;
	private int modelId;
	private String colorsAvailable;
	
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public String getColorsAvailable() {
		return colorsAvailable;
	}
	public void setColorsAvailable(String colorsAvailable) {
		this.colorsAvailable = colorsAvailable;
	}
	

}
