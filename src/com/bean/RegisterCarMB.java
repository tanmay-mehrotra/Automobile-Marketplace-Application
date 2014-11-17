package com.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.TO.CarTO;
import com.exception.NoSuchCarException;
import com.exception.NoUnregsiteredCarsException;
import com.wrapper.AutoSellWrapper;

public class RegisterCarMB {
	private List<SelectItem> chassisNoList = new ArrayList<SelectItem>();
	private String chassisNumber;
	private List<CarTO> listOfCars;
	private String message;
	public List<SelectItem> getChassisNoList() {
		return chassisNoList;
	}
	public void setChassisNoList(List<SelectItem> chassisNoList) {
		this.chassisNoList = chassisNoList;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	public List<CarTO> getListOfCars() {
		return listOfCars;
	}
	public void setListOfCars(List<CarTO> listOfCars) {
		this.listOfCars = listOfCars;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public RegisterCarMB()
	{
		AutoSellWrapper wrapper = new AutoSellWrapper();
		
		try 
		{
			this.listOfCars = wrapper.getUnregisteredCars();
			Iterator<CarTO> i = listOfCars.iterator();
			while(i.hasNext())
			{
				CarTO carTo = (CarTO)i.next();
				SelectItem selectItem = new SelectItem(carTo.getChassisNo().toString());
				this.chassisNoList.add(selectItem);
			}
		} 
		catch (NoUnregsiteredCarsException e) 
		{	
			this.setMessage(e.getMessage());
		}
		
	}
	
	public String registerCar() 
	{
		AutoSellWrapper wrapper = new AutoSellWrapper();
		try
		{
			String registrationNo = wrapper.registerCar(this.chassisNumber);
			this.setMessage("Car with Registration Number: "+registrationNo+" registered succesfully!");
			return "success";
		}
		catch(NoSuchCarException e)
		{
			this.setMessage(e.getMessage());
			return "Failure";
		}
	}

}
