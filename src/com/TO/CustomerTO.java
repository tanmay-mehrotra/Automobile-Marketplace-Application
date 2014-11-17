package com.TO;

public class CustomerTO {
	
	private int customerId;
	private String userId;
	private String name;
	private String emailId;
	private String phoneNumber;
	private int prospectCustomerId;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getProspectCustomerId() {
		return prospectCustomerId;
	}
	public void setProspectCustomerId(int prospectCustomerId) {
		this.prospectCustomerId = prospectCustomerId;
	}

}
