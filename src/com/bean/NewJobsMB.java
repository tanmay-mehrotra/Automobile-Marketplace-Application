package com.bean;
/**
 * this class provide general purpose method for string validation
 *
 * @author Abhinav Keshari
 * @version 1.1
 */

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import com.TO.EnquiryTO;
import com.exception.NoJobsAssignedException;
import com.wrapper.AutoSellWrapper;

public class NewJobsMB {
	private List<EnquiryTO> enquiryList;
	private Integer requestId;
	private List<SelectItem> requestIdList;
	private String message;

	//Constructor for populating the respective list at startup
	public NewJobsMB()
	{
		//Instantiating the wrapper Class object
		AutoSellWrapper wrapper=new AutoSellWrapper();
		//Retreiving attribute employeeId from session 
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		Integer employeeId=(Integer)session.getAttribute("user");
		//initializing the references
		SelectItem selectItem;
		EnquiryTO enquiryTO;
		this.requestIdList=new ArrayList<SelectItem>();

		try
		{
			/*calling the getNewEnquiry method of the wrapper function 
			to populate the Enquiry List*/
			this.enquiryList=wrapper.getNewEnquiry(employeeId);

			//Generating a list of type select items to display the request Ids		
			for(int count=0;count<enquiryList.size();count++)
			{
				enquiryTO= enquiryList.get(count);
				selectItem = new SelectItem(enquiryTO.getRequestId());
				this.requestIdList.add(selectItem);
			}

		}
		//Catching Exceptions
		catch(NoJobsAssignedException e)
		{
			this.setMessage(e.getMessage());
		}
		catch (Exception e)
		{
			this.setMessage(e.getMessage());
		}
	}

	//Getters and Setters for the instance variables
	public List<EnquiryTO> getEnquiryList() {
		return enquiryList;
	}
	public void setEnquiryList(List<EnquiryTO> enquiryList) {
		this.enquiryList = enquiryList;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId){
		this.requestId = requestId;
	}

	public List<SelectItem> getRequestIdList(){
		return requestIdList;
	}
	public void setRequestIdList(List<SelectItem> requestIdList) {
		this.requestIdList = requestIdList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	/*Method to set the Request id as session attribute and transfer control 
	  to next jsp*/
	public String getEnquiryDetails()
	{

		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		session.setAttribute("requestId",this.getRequestId());
		return "success";

	}

}
