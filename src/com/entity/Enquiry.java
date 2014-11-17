package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Enquiry {
	@Id
	@SequenceGenerator(name="seq_RequestId", sequenceName="seq_RequestId",initialValue=5004,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_RequestId")
	private int requestId;
	private int modelId;
	@Column(name="prospectiveCustomerID")
	private int prospectCustomerId;
	private char status;
	@Temporal(TemporalType.DATE)
	private Date dateOfRequest;
	private int colorId;
	@Column(name="marketingExecutiveId")
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
	public int getProspectCustomerId() {
		return prospectCustomerId;
	}
	public void setProspectCustomerId(int prospectCustomerId) {
		this.prospectCustomerId = prospectCustomerId;
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
