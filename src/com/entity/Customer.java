package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {
	@Id
	@SequenceGenerator(name="seq_CustomerId", sequenceName="seq_CustomerId",initialValue=7003,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_CustomerId")
	private int customerId;
	private String userId;
	private String name;
	@Column(name="email")
	private String emailId;
	private String phoneNumber;
	@Column(name="prospectiveCustomerId")
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
