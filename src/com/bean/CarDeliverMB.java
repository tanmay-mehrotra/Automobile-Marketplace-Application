package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.TO.CarTO;
import com.exception.NoRegisteredCarsException;
import com.exception.NoSuchCarException;

import com.wrapper.AutoSellWrapper;

public class CarDeliverMB {

	private String chassisNumber;
	private List<SelectItem> chassisNoList;
	private List<CarTO> listOfCars;
	private String message;


	public String getChassisNumber() {
		return chassisNumber;
	}

	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}

	public List<SelectItem> getChassisNoList() {
		return chassisNoList;
	}

	public void setChassisNoList(List<SelectItem> chassisNoList) {
		this.chassisNoList = chassisNoList;
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

	public CarDeliverMB()
	{
		AutoSellWrapper wrapper = new AutoSellWrapper();
	
		this.chassisNoList= new ArrayList<SelectItem>();
		try {
			this.listOfCars = wrapper.getUndeliveredCars();
			Iterator<CarTO> i = listOfCars.iterator();
			while(i.hasNext())
			{
				CarTO carTo = (CarTO)i.next();
				SelectItem selectItem = new SelectItem(carTo.getChassisNo(), carTo.getRegistrationNo().toString());
				this.chassisNoList.add(selectItem);
			}
		} 
		catch (NoRegisteredCarsException e) 
		{
			this.setMessage(e.getMessage());			
		}
	} 

	public String deliverCar() 
	{
		AutoSellWrapper wrapper = new AutoSellWrapper();
		try
		{
			boolean value = wrapper.deliverCar(this.chassisNumber);
			if(value == true)
			{
				Date date= new Date(); 
				this.setMessage("Car delivered :"+ date);
				return "success";
			}
			else
			{
				this.setMessage("Updation failed");
				return "failure"; 
			}
		}
		catch(NoSuchCarException e)
		{
			this.setMessage(e.getMessage());
			return "Failure";
		}
	}
}


