package com.TO;

import java.util.Date;


public class EnquiryTO {
	
	private int requestId;
	private int modelId;
	private int prospectiveCustomerId;
	private char status;
	private Date dateOfRequest;
	private int colorId;
	private int marketExecutiveId;
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public int getProspectiveCustomerId() {
		return prospectiveCustomerId;
	}
	public void setProspectiveCustomerId(int prospectiveCustomerId) {
		this.prospectiveCustomerId = prospectiveCustomerId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Date getDateOfRequest() {
		return dateOfRequest;
	}
	public void setDateOfRequest(Date dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}
	public int getColorId() {
		return colorId;
	}
	public void setColorId(int colorId) {
		this.colorId = colorId;
	}
	public int getMarketExecutiveId() {
		return marketExecutiveId;
	}
	public void setMarketExecutiveId(int marketExecutiveId) {
		this.marketExecutiveId = marketExecutiveId;
	}
	
	
	
	

}
