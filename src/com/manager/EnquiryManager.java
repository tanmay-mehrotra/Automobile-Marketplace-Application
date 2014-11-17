package com.manager;

import java.util.ArrayList;
import java.util.List;
import com.TO.CarTO;
import com.TO.CustomerTO;
import com.TO.EmployeeTO;
import com.TO.EnquiryDetailsTO;
import com.TO.EnquiryQuotationTO;
import com.TO.EnquiryTO;
import com.TO.LoginTO;
import com.TO.ModelTO;
import com.TO.OfferTO;
import com.TO.ProspectiveCustomerTO;
import com.TO.QuotationTO;
import com.exception.NoEmployeeFoundException;
import com.exception.NoJobsAssignedException;
import com.service.CarService;
import com.service.CustomerService;
import com.service.EmployeeService;
import com.service.EnquiryService;
import com.service.LoginService;
import com.service.ModelService;
import com.service.OfferService;
import com.service.ProspectiveCustomerService;
import com.service.QuotationService;

public class EnquiryManager {

	EnquiryService enquiryService=new EnquiryService(); 
	CarService carService=new CarService();
	QuotationService quotationService=new QuotationService();
	ProspectiveCustomerService prospectiveCustomerService=new ProspectiveCustomerService();
	EnquiryQuotationTO enquiryQuotationTO=new EnquiryQuotationTO();
	List<EnquiryQuotationTO> enquiryQuotationTO1= new ArrayList<EnquiryQuotationTO>(); 




	public List<EnquiryTO> getFollowUpEnquiry(int employeeId) throws Exception
	{ 	List<EnquiryTO> list=enquiryService.getFollowUpEnquiry(employeeId);
	return list;
	}




	public List<EnquiryQuotationTO> getQuotation(Integer employeeId) throws Exception
	{  
		List<EnquiryQuotationTO> list1=new ArrayList<EnquiryQuotationTO>();
		//session variable employeeId//
		//List<EnquiryTO> list=getFollowUpEnquiry(employeeId);//
		List<EnquiryTO> list=getFollowUpEnquiry(employeeId);

		for(int i=0;i<list.size();i++)
		{	

			EnquiryQuotationTO enquiryQuotationTO=getEnquiryQuotationDetails(list.get(i).getRequestId());
			list1.add(enquiryQuotationTO);
		}   	
		return list1;
	}


	public EnquiryQuotationTO getEnquiryQuotationDetails(Integer requestId) throws Exception
	{   

		EnquiryQuotationTO enquiryQuotationTO=new EnquiryQuotationTO();
		ModelService modelService=new ModelService();
		OfferService offerService=new OfferService();
		QuotationService  quotationService=new QuotationService();
		ProspectiveCustomerService prospectiveCustomerService= new ProspectiveCustomerService(); 

		EnquiryTO  enquiryTO=enquiryService.getEnquiryDetails(requestId);

		ModelTO modelTO=modelService.getModel(enquiryTO.getModelId());

		List<OfferTO> offerTOList=offerService.getOffers();

		ProspectiveCustomerTO prospectiveCustomerTO=prospectiveCustomerService.getProspectiveCustomer(enquiryTO.getProspectiveCustomerId());
		QuotationTO quotationTO=quotationService.getQuotation(requestId);
		enquiryQuotationTO.setModelTO(modelTO);
		enquiryQuotationTO.setOfferTOList(offerTOList);
		enquiryQuotationTO.setProspectiveCustomerTO(prospectiveCustomerTO);
		enquiryQuotationTO.setQuotaionTO(quotationTO);

		return enquiryQuotationTO;
	}



	public Boolean refuseBooking(int quotationId) throws Exception
	{
		QuotationManager quotationManager=new QuotationManager();
		char bookingStatus='R';
		QuotationTO quotationTO=quotationManager.updateQuotationStatus(quotationId,bookingStatus);
		Boolean ret=enquiryService.updateEnquiryStatus(quotationTO.getRequestId(),bookingStatus);
		return ret;	
	}

	public Boolean acceptBooking(int quotationId)throws Exception
	{

		QuotationTO quotationTO=quotationService.getQuotationById(quotationId);
		EnquiryTO enquiryTO=enquiryService.getEnquiryDetails(quotationTO.getRequestId());
		String chassisNo=carService.getCar(enquiryTO.getModelId(),enquiryTO.getColorId());	

		QuotationManager quotationManager=new QuotationManager();
		char bookingStatus='B';
		quotationManager.updateQuotationStatus(quotationId,bookingStatus);
		Boolean ret=enquiryService.updateEnquiryStatus(quotationTO.getRequestId(),bookingStatus);

		ProspectiveCustomerTO prospectiveCustomerTO=prospectiveCustomerService.getProspectiveCustomer(enquiryTO.getProspectiveCustomerId());
		CustomerTO customerTO=new CustomerTO();

		customerTO.setEmailId(prospectiveCustomerTO.getEmailId());
		customerTO.setName(prospectiveCustomerTO.getCustomerName());
		customerTO.setPhoneNumber(prospectiveCustomerTO.getPhoneNumber());
		customerTO.setProspectCustomerId(prospectiveCustomerTO.getProspectCustomerId());
		customerTO.setUserId(prospectiveCustomerTO.getCustomerName()+"123");
		CustomerService customerService=new CustomerService();

		int custId=customerService.addCustomer(customerTO);

		/*LOGIN TO********/
		LoginTO loginTO=new LoginTO();
		loginTO.setUserId(prospectiveCustomerTO.getCustomerName()+"123");
		loginTO.setPassWord("infy");
		loginTO.setRole('C');
		LoginService loginService=new LoginService();
		loginService.addUser(loginTO);



		/*allocate car*/
		CarTO carTO=new CarTO();
		carTO.setChassisNo(chassisNo);
		carTO.setCustId(custId);
		carTO.setModelId(enquiryTO.getModelId());
		carTO.setColorId(enquiryTO.getColorId());
		carService.addCar(carTO);
		return ret;	
	}

	public List<EnquiryTO> getNewEnquiry(Integer employeeId)throws NoJobsAssignedException {

		/*Instantiating Object Of enquiry Service
		  and calling appropriate method*/
		EnquiryService enquiryService=new EnquiryService();
		return enquiryService.getNewEnquiry(employeeId);

	}

	public EnquiryDetailsTO getEnquiryDetails(int requestId)throws Exception{

		/*Instantiating Object Of enquiry Service
		  and calling appropriate method*/
		EnquiryService enquiryService=new EnquiryService();
		EnquiryTO enquiryTO=enquiryService.getEnquiryDetails(requestId);

		/*Instantiating Object Of ProspectiveCustomer Service
		  and calling appropriate method*/
		ProspectiveCustomerService prospectCustomerService=new ProspectiveCustomerService();
		ProspectiveCustomerTO prospectCustomerTO=prospectCustomerService.getProspectiveCustomer(enquiryTO.getProspectiveCustomerId());

		/*Instantiating Object Of Model Service
		  and calling appropriate method*/
		ModelService modelService=new ModelService();
		ModelTO modelTO=modelService.getModel(enquiryTO.getModelId());

		/*Instantiating Object Of Offer Manager Service
		  and calling appropriate method*/
		OfferManager offerManager=new OfferManager();
		List<OfferTO> listOfferTo=offerManager.getOffers();

		EnquiryDetailsTO enquiryDetailsTO=new EnquiryDetailsTO();
		//Setting EnquiryDetailsTO object according to the returned data
		enquiryDetailsTO.setModelTO(modelTO);
		enquiryDetailsTO.setProspectiveCustomerTO(prospectCustomerTO);
		enquiryDetailsTO.setOfferTOList(listOfferTo);
		return enquiryDetailsTO;
	}


	public EnquiryTO getEnquiryDetails(Integer requestId) throws Exception
	{
		EnquiryTO enquiry=new EnquiryService().getEnquiryDetails(requestId);
		return enquiry;
	}

	public List<EnquiryTO> getNewEnquiry(int employeeId) throws NoJobsAssignedException{

		EnquiryService enquiryService=new EnquiryService();
		List<EnquiryTO> list=new ArrayList<EnquiryTO>();

		list=enquiryService.getNewEnquiry(employeeId);

		return list;

	}
	public EmployeeTO addEnquiry(EnquiryTO enquiryTO,ProspectiveCustomerTO prospectCustomerTO) throws NoEmployeeFoundException{
		/*Calling getMarketingExecutive() of EmployeeService */
		EmployeeTO employeeTO=new EmployeeService().getMarketingExecutive();
		ProspectiveCustomerService pcService=new ProspectiveCustomerService();
		/*Calling addProspectCustomer method to persist new prospective customer to the database*/
		ProspectiveCustomerTO prospectCustomerTO1=pcService.addProspectCustomer(prospectCustomerTO);
		/* Populating enquiry object with marketingExecutiveId returned from getMarketingExecutive() function call*/
		enquiryTO.setMarketExecutiveId(employeeTO.getEmpId());
		/*Populating enquiry object with prospectCustomerId returned after prospective customer is persisted and propspectiveCustomerId is returned*/
		enquiryTO.setProspectiveCustomerId(prospectCustomerTO1.getProspectCustomerId());
		EnquiryService eService=new EnquiryService();
		@SuppressWarnings("unused")
		/*Calling addEnquiry() of EmployeeService to persist the enquiry object to the database*/
		boolean result=eService.addEnquiry(enquiryTO);
		/*EmployeeTo object is returned after the new enquiry is persisted to the database*/
		return employeeTO;

	}

}

















