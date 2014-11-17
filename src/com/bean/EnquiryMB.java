package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import com.TO.EmployeeTO;
import com.TO.EnquiryTO;
import com.TO.ModelColorsTO;
import com.TO.ModelTO;
import com.TO.ProspectiveCustomerTO;
import com.exception.InvalidModelIdException;
import com.exception.NoEmployeeFoundException;
import com.exception.NoModelsException;
import com.wrapper.AutoSellWrapper;

public class EnquiryMB {
	private ModelTO model;
	private List<SelectItem> colorList;
	private Integer colorId;
	private String customerName;
	private String phoneNo;
	private String mailId;
	private String message;

	/**
	 * @return the model
	 */
	public ModelTO getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(ModelTO model) {
		this.model = model;
	}

	/**
	 * @return the colorList
	 */
	public List<SelectItem> getColorList() {
		return colorList;
	}

	/**
	 * @param colorList the colorList to set
	 */
	public void setColorList(List<SelectItem> colorList) {
		this.colorList = colorList;
	}

	/**
	 * @return the colorId
	 */
	public Integer getColorId() {
		return colorId;
	}

	/**
	 * @param colorId the colorId to set
	 */
	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}

	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public EnquiryMB(){


		AutoSellWrapper wrapper=new AutoSellWrapper();
		/*Retrieving the session attribute 'modelid' which is set in the search car module*/
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		Integer modelId=(Integer) session.getAttribute("modelId");

		model=new ModelTO();

		/*Setting the model property to the model Id extracted from the session*/
		try {
			this.model=wrapper.getModel(modelId);
		} 
		catch (InvalidModelIdException e)
		{

			/*Exception thrown if model id is invalid*/
			this.setMessage(e.getMessage());
		}
		catch (NoModelsException e) 
		{
			/*Exception thrown if models are not present in the database*/
			this.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			this.setMessage(e.getMessage());
		}

		try{
			/*Populating the colorList property using getModelColor() method of ModelColorService class*/
			List<ModelColorsTO> list1 = new ArrayList<ModelColorsTO>();
			list1 = new AutoSellWrapper().getModelColor(modelId);
			this.colorList = new ArrayList<SelectItem>();

			Iterator<ModelColorsTO> iter = list1.iterator();
			while(iter.hasNext()){
				ModelColorsTO items2 = iter.next();
				SelectItem item = new SelectItem(items2.getColorId(),items2.getColorsAvailable());
				this.colorList.add(item);

			}
		}
		catch(InvalidModelIdException exception){
			/* Exception thrown if model id entered is invalid*/
			this.setMessage(exception.getMessage());

		}

	}

	public String addEnquiry(){
		/*This method will add a new enquiry to the database*/
		this.setMessage(null);
		AutoSellWrapper wrapper=new AutoSellWrapper();
		/*Populating ProspectCustomerTO object to persist it into the database
		 * this will return prospective customer Id which will be used to persist enquiry object to the database*/
		ProspectiveCustomerTO prospectCustomerTO=new ProspectiveCustomerTO();
		prospectCustomerTO.setCustomerName(this.getCustomerName());
		prospectCustomerTO.setEmailId(this.getMailId());
		prospectCustomerTO.setPhoneNumber(this.getPhoneNo());
		EnquiryTO enquiryTO=new EnquiryTO();
		enquiryTO.setColorId(this.getColorId());
		enquiryTO.setStatus('E');
		/*modelId is extracted from the session*/
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);

		enquiryTO.setModelId((Integer)(session.getAttribute("modelId")));
		/*setting date of request to current date*/
		Date date= new Date();
		enquiryTO.setDateOfRequest(date);
		try {
			/*calling addEnquiry() method of EnquiryService class*/
			EmployeeTO employeeTO=wrapper.addEnquiry(enquiryTO,prospectCustomerTO);
			this.setMessage("You will be shortly contacted by our customer care executive Mr\\Ms:"+employeeTO.getEmpName()+".You can contact him on:"+" "+employeeTO.getPhoneNumber()+" "+"for further information");



		} catch (NoEmployeeFoundException e) {
			/*Exception thrown if no marketing executive is found*/
			this.setMessage(e.getMessage());

			return "failed";
		}

		return "successfullyadded";





	}


	public void validateName(FacesContext facesContext, UIComponent component, Object object)throws ValidatorException
	{
		if(facesContext==null || component==null){
			throw new NullPointerException();
		}
		if(!(component instanceof UIInput)){
			return;
		}
		/*Instantiating FacesMessage*/
		FacesMessage message =new FacesMessage();
		message.setSummary("Name can contain only characters and spaces");
		String userName=object.toString();
		for(int count=0;count<userName.length();count++)
		{
			int temp=userName.charAt(count);
			System.out.println(temp);
			/*checking whether the character is a number*/
			if( (temp>=48) && (temp<=57) )
			{
				/*throwing ValidatorException if the	character is a number*/
				message.setDetail("Name can contain only characters and spaces");
				throw new ValidatorException(message);
			}
		}
	}
	public static boolean isNumeric(String strInput)
	{

		boolean bResult = false;
		try
		{
			if(strInput != null)
			{
				strInput = strInput.trim();

				Double.parseDouble(strInput);
				bResult =  true;

			}
			else
			{
				bResult = false;
			}
		}
		catch(NumberFormatException nfe)
		{
			bResult = false;
		}

		return bResult;
	}    
	public void validatePhoneNumber(FacesContext facesContext, UIComponent component, Object object)throws ValidatorException
	{
		if(facesContext==null || component==null){
			throw new NullPointerException();
		}
		if(!(component instanceof UIInput)){
			return;
		}
		/*Instantiating FacesMessage*/
		FacesMessage message =new FacesMessage();
		message.setSummary("Name can contain only characters and spaces");
		String string=object.toString();
		boolean bool=isNumeric(string);
		if(bool==false ||( string.length()<10|| string.length()>11))
		{
			/*throwing ValidatorException if the	character is a number*/
			message.setDetail("Phone number can have 10 or 11 digits");
			throw new ValidatorException(message);
		}

	}
	public void validateEmail(FacesContext facesContext, UIComponent component, Object object)throws ValidatorException
	{  
		int counter=0;
		int counter1=0;
		if(facesContext==null || component==null){
			throw new NullPointerException();
		}
		if(!(component instanceof UIInput)){
			return;
		}
		/*Instantiating FacesMessage*/
		FacesMessage message =new FacesMessage();
		message.setSummary("Invalid E-mail Id");

		String email=object.toString();
		for(int count=0;count<email.length();count++)
		{
			int temp=email.charAt(count);
			System.out.println(temp);
			/*checking whether the character is a number*/
			if (temp==64)  
			{
				/*throwing ValidatorException if the mcharacter is a number*/
				counter++;
			}
			if(counter==1 && temp==46){
				counter1++;
			}
		}
		if(counter!=1 || counter1!=1){
			throw new ValidatorException(message);
		}
	}
}
