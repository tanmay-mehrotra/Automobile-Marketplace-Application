package com.manager;

import java.util.List;

import com.TO.CarTO;
import com.exception.NoCarsBookedException;
import com.exception.NoRegisteredCarsException;
import com.exception.NoSuchCarException;
import com.exception.NoUnregsiteredCarsException;

import com.service.CarService;

public class CarManager {

	public List<CarTO> getUnregisteredCars()throws NoUnregsiteredCarsException 
	{
		CarService carService = new CarService();
		List<CarTO> carList = carService.getUnregisteredCars();
		return carList;
	}

	public String registerCar(String chassisNo) throws NoSuchCarException
	{
		CarService carService = new CarService();
		String registrationNo = carService.registerCar(chassisNo);
		return registrationNo;
	}

	public boolean deliverCar(String chassisNo) throws NoSuchCarException
	{
		CarService carService = new CarService();
		boolean value = carService.deliverCar(chassisNo);
		return value;
	}


	public	List<CarTO> getUndeliveredCars() throws NoRegisteredCarsException
	{		
		CarService carService = new CarService();
		List<CarTO> carList= carService.getUndeliveredCars();
		return carList;
	}


	public CarTO getCar(String chassisNo) throws NoSuchCarException{
		CarService service =new CarService();
		CarTO carTO =service.getCar(chassisNo);
		return carTO;

	}

	public List<CarTO> getCustomerCar() throws NoCarsBookedException{
		CarService service =new CarService();
		List<CarTO> list=service.getCustomerCar();
		return list;

	}
	public List<CarTO> getCustomerCar(int customerId) throws NoCarsBookedException{


		CarService carService=new CarService();
		List<CarTO> list=carService.getCustomerCar(customerId);
		return list;

	}


	public boolean addCar(CarTO carTO){
		/*Calling addCar() method of CarService*/
		CarService carService=new CarService();
		carService.addCar(carTO);
		/*On successful addition to the database it will return true*/
		return true;


	}
}
