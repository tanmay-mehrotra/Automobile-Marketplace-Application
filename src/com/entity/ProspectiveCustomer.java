package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class ProspectiveCustomer {
	@Id
	@SequenceGenerator(name="seq_ProspectiveCustomerId", sequenceName="seq_ProspectiveCustomerId",initialValue=12004,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_ProspectiveCustomerId")
	private int prospectiveCustomerId;
	private String customerName;
	@Column(name="phonenumber")
	private String phoneNo;
	private String emailId;
	public int getProspectiveCustomerId() {
		return prospectiveCustomerId;
	}
	public void setProspectiveCustomerId(int prospectiveCustomerId) {
		this.prospectiveCustomerId = prospectiveCustomerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	}
	
