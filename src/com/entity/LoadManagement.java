package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class LoadManagement {
	@Id
	private int employeeId;
	private int noOfJobs;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getNoOfJobs() {
		return noOfJobs;
	}
	public void setNoOfJobs(int noOfJobs) {
		this.noOfJobs = noOfJobs;
	}
	

}
