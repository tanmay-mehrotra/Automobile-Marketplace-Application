package com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.exception.InvalidCustomerIdException;
import com.TO.CarTO;
import com.wrapper.AutoSellWrapper;

public class CarStatusMB {


	private List<SelectItem> chassisNoList=new ArrayList<SelectItem>();
	private String chassisNo;
	private CarTO carTo;
	private String message;


	public List<SelectItem> getChassisNoList() {
		return chassisNoList;
	}
	public void setChassisNoList(List<SelectItem> chassisNoList) {
		this.chassisNoList = chassisNoList;
	}
	public String getChassisNo() {
		return chassisNo;
	}
	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}
	public CarTO getCarTo() {
		return carTo;
	}
	public void setCarTo(CarTO carTo) {
		this.carTo = carTo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CarStatusMB(){

		AutoSellWrapper autoSellWrapper=new AutoSellWrapper();
		List<CarTO> list=new ArrayList<CarTO>();
		try{
			FacesContext faces =FacesContext.getCurrentInstance();
			ExternalContext et=faces.getExternalContext();
			HttpSession session =(HttpSession) et.getSession(true);
			int custId=(Integer)session.getAttribute("user");
			list=autoSellWrapper.getCustomerCar(custId);
			if(list.size()==0){
				throw new InvalidCustomerIdException();
			}
			else{
				for(int i=0;i<=list.size();i++){

					this.chassisNoList.add(new SelectItem(list.get(i).getChassisNo()));
				}
			}
		}
		catch(Exception e){
			this.setMessage(e.getMessage());
		}

	}

	public void getCar (ValueChangeEvent event){

		this.chassisNo=(String)event.getNewValue();
		AutoSellWrapper autoSellWrapper=new AutoSellWrapper();
		try{

			this.carTo=autoSellWrapper.getCar(chassisNo);
			System.out.println(carTo.getChassisNo());

		}catch(Exception e){
			this.setMessage(e.getMessage());
		}

	}

}
