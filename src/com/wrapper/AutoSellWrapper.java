package com.wrapper;

import java.util.ArrayList;
import java.util.List;
import com.TO.CarTO;
import com.TO.CustomerTO;
import com.TO.EmployeeTO;
import com.TO.EnquiryDetailsTO;
import com.TO.EnquiryQuotationTO;
import com.TO.EnquiryTO;
import com.TO.LoginTO;
import com.TO.ModelColorsTO;
import com.TO.ModelTO;
import com.TO.OfferTO;
import com.TO.ProspectiveCustomerTO;
import com.TO.QuotationTO;
import com.exception.InvalidModelIdException;
import com.exception.InvalidOfferIdException;
import com.exception.InvalidQuotationIdException;
import com.exception.InvalidUserException;
import com.exception.NoCarsBookedException;
import com.exception.NoEmployeeFoundException;
import com.exception.NoJobsAssignedException;
import com.exception.NoModelsException;
import com.exception.NoRegisteredCarsException;
import com.exception.NoSuchCarException;
import com.exception.NoUnregsiteredCarsException;
import com.manager.CarManager;
import com.manager.CustomerManager;
import com.manager.EmployeeManager;
import com.manager.EnquiryManager;
import com.manager.LoginManager;
import com.manager.ModelColorManager;
import com.manager.ModelManager;
import com.manager.OfferManager;
import com.manager.QuotationManager;


public class AutoSellWrapper {

	EnquiryManager enquiryManager=new EnquiryManager();

	public List<EnquiryQuotationTO>getQuotation(int employeeId) throws Exception
	{ 

		List<EnquiryQuotationTO> list=enquiryManager.getQuotation(employeeId);
		return list;
	}

	public Boolean acceptBooking(int quotationId) throws Exception
	{  

		Boolean ret=enquiryManager.acceptBooking(quotationId);
		return  ret;
	}

	public Boolean refuseBooking(int quotationId) throws Exception
	{
		Boolean ret=enquiryManager.refuseBooking(quotationId);
		return ret;
	}
	public List<CarTO> getUnregisteredCars()throws NoUnregsiteredCarsException 
	{
		CarManager carManager = new CarManager();
		List<CarTO> carList = carManager.getUnregisteredCars();
		return carList;
	}

	public String registerCar(String chassisNo) throws NoSuchCarException
	{
		CarManager carManager = new CarManager();
		String registrationNo = carManager.registerCar(chassisNo);
		return registrationNo;
	}

	public boolean deliverCar(String chassisNo) throws NoSuchCarException
	{
		CarManager carManager = new CarManager();
		boolean value = carManager.deliverCar(chassisNo);
		return value;
	}

	public	List<CarTO> getUndeliveredCars() throws NoRegisteredCarsException
	{
		CarManager carManager = new CarManager();
		List<CarTO> carList = carManager.getUndeliveredCars();
		return carList;
	}

	public LoginTO validatePassword(String userName, String password) throws InvalidUserException
	{
		LoginManager loginManager = new LoginManager();
		LoginTO loginTO= loginManager.validatePassword(userName, password);
		return loginTO;
	}

	public CustomerTO getCustomer(String userName) throws InvalidUserException
	{
		CustomerManager customerManager=new CustomerManager();
		CustomerTO customerTO=customerManager.getCustomer(userName);
		return customerTO;

	}

	public EmployeeTO getEmployee(String userName) throws NoEmployeeFoundException
	{
		EmployeeManager employeeManager=new EmployeeManager();
		EmployeeTO employeeTO=employeeManager.getEmployee(userName);
		return employeeTO;
	}
	public List<EnquiryTO> getNewEnquiry(Integer employeeId)throws NoJobsAssignedException {

		EnquiryManager enquiryManager=new EnquiryManager();
		return enquiryManager.getNewEnquiry(employeeId);

	}

	public EnquiryDetailsTO getEnquiryDetails(int requestId)throws Exception {


		EnquiryManager enquiryManager=new EnquiryManager();
		return enquiryManager.getEnquiryDetails(requestId);

	}

	public QuotationTO generateQuotation(QuotationTO quotationTO) throws Exception {

		QuotationManager quotationManager=new QuotationManager();
		return quotationManager.generateQuotation(quotationTO);

	}

	public OfferTO getOffer(Integer offerId) throws InvalidOfferIdException {

		OfferManager offerManager=new OfferManager();
		return offerManager.getOffer(offerId);

	}

	public List<OfferTO> getOffers() throws Exception{

		OfferManager offerManager=new OfferManager();
		return offerManager.getOffers();

	}



	public List<ModelTO> getModels() throws NoModelsException
	{

		ModelManager modelManager=new ModelManager();
		return modelManager.getModels();

	}

	public List<ModelTO> getModelByType(String carType) throws NoModelsException
	{
		ModelManager modelManager=new ModelManager();
		return modelManager.getModelByType(carType);

	}
	public List<String> getDistinctCarType () throws NoModelsException
	{
		ModelManager modelManager=new ModelManager();
		return modelManager.getDistinctCarType();
	}
	public List<ModelTO> getModelByPrice(double startPrice,double endPrice ) throws NoModelsException{
		List<ModelTO> list = null;
		ModelManager modelmanager = new ModelManager();
		list = modelmanager.getModelByPrice(startPrice, endPrice);
		return list ;
	}


	public ModelTO getModel(int modelId) throws NoModelsException, InvalidModelIdException,Exception {
		ModelManager modelManager=new ModelManager();
		ModelTO modelTO=modelManager.getModel(modelId);
		return modelTO;
	}
	public boolean updateModel(int modelId,double price) throws InvalidModelIdException{
		ModelManager modelmanager = new ModelManager();
		modelmanager.updateModel(modelId, price);
		return true;
	}
	public boolean withdrawModel(int modelId)throws Exception{
		ModelManager modelmanager = new ModelManager();
		modelmanager.withdrawModel(modelId);
		return true;
	}


	public int addModel(ModelTO modelTO){
		ModelManager manager= new ModelManager();

		int modelId= manager.addModel(modelTO);
		return modelId;
	}

	public List<String> getDistinctModel() throws NoModelsException{
		ModelManager manager= new ModelManager();
		List<String> list=manager.getDistinctModels();
		return list;

	}

	public List<ModelTO> getModelByName(String modelName) throws NoModelsException{ 
		ModelManager manager= new ModelManager();
		List<ModelTO> list=manager.getModelByName(modelName);
		return list;

	}

	public CarTO getCar(String chassisNo) throws NoSuchCarException{ 
		CarManager carmanager= new CarManager();
		CarTO carTO=carmanager.getCar(chassisNo);
		return carTO;

	}

	public List<CarTO> getCustomerCar() throws NoCarsBookedException { 
		CarManager manager= new CarManager();
		List<CarTO> list=manager.getCustomerCar();
		return list;

	}

	public List<EnquiryTO> getFollowUpEnquiry(int employeeId) throws Exception
	{
		EnquiryManager manager=new EnquiryManager();
		List<EnquiryTO> list=manager.getFollowUpEnquiry(employeeId);
		return list;

	}


	public QuotationTO updateQuotation(QuotationTO quotationTO)throws InvalidQuotationIdException
	{
		QuotationTO qTO=new QuotationManager().updateQuotation(quotationTO);
		return qTO;

	}
	public EnquiryQuotationTO getEnquiryQuotationDetails(Integer requestId)throws Exception
	{
		EnquiryQuotationTO enquiryQuotationTO=new EnquiryManager().getEnquiryQuotationDetails(requestId);
		return enquiryQuotationTO;
	}
	public List<EnquiryTO> getNewEnquiry(int employeeId) throws NoJobsAssignedException{
		EnquiryManager enquiryManager=new EnquiryManager();
		List<EnquiryTO> list=new ArrayList<EnquiryTO>();
		list=enquiryManager.getNewEnquiry(employeeId);
		return list;

	}


	public List<String> getDistinctModels () throws NoModelsException{
		ModelManager modelManager=new ModelManager();
		List<String> list=new ArrayList<String>();
		list=modelManager.getDistinctModels();
		return list;
	}




	public List<CarTO> getCustomerCar(int customerId) throws NoCarsBookedException{


		CarManager carManager=new CarManager();
		List<CarTO> list=carManager.getCustomerCar(customerId);
		return list;

	}


	public List<ModelColorsTO> getModelColor(Integer modelId) throws InvalidModelIdException {
		ModelColorManager modelManager=new ModelColorManager();
		List<ModelColorsTO> list=modelManager.getModelColor(modelId);
		return list;
	}



	public boolean addCar(CarTO carTO) {

		CarManager carManager=new CarManager();
		carManager.addCar(carTO);
		return true;


	}


	public String changePassword(String userName, String oldPassword,String password) throws InvalidUserException {

		LoginManager loginManager=new LoginManager();
		boolean result=loginManager.changePassword(userName,oldPassword,password);

		if(result==true)
			return "passwordchanged";
		else
			return "Invalid";
	}

	public EmployeeTO addEnquiry(EnquiryTO enquiryTO,ProspectiveCustomerTO prospectCustomerTO) throws NoEmployeeFoundException{
		EnquiryManager enquiryManager=new EnquiryManager();
		EmployeeTO employeeTO=enquiryManager.addEnquiry(enquiryTO,prospectCustomerTO);
		return employeeTO;



	}




}
