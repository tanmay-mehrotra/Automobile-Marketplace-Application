package com.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.TO.EnquiryQuotationTO;
import com.wrapper.AutoSellWrapper;


public class UpdateEnquiryStatusMB {

	private List<EnquiryQuotationTO> enquiryQuotaionTOList;
	private int quotationId;
	private List<SelectItem> quotationIdList=new ArrayList<SelectItem>();;
	private EnquiryQuotationTO enquiryQuotationTO;
	private String message;

	public UpdateEnquiryStatusMB()
	{   
		try
		{  
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext etx = ctx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);
			int empId=(Integer)session.getAttribute("user");

			AutoSellWrapper autoSellWrapper = new AutoSellWrapper ();
			this.enquiryQuotaionTOList=autoSellWrapper.getQuotation(empId);

			Iterator<EnquiryQuotationTO> enquiryIter = enquiryQuotaionTOList.iterator();
			while(enquiryIter.hasNext())
			{
				EnquiryQuotationTO enquiryQuotationTO = enquiryIter.next();
				SelectItem item = new SelectItem(enquiryQuotationTO.getQuotaionTO().getQuotationId());
				quotationIdList.add(item);

			}

		}
		catch(Exception exception)
		{

			this.setMessage(exception.getMessage());
		}
	}

	/**
	 * Getters and setters of all instance variable 
	 */
	public List<EnquiryQuotationTO> getEnquiryQuotaionTOList() {
		return enquiryQuotaionTOList;
	}
	public void setEnquiryQuotaionTOList(
			List<EnquiryQuotationTO> enquiryQuotaionTOList) {
		this.enquiryQuotaionTOList = enquiryQuotaionTOList;
	}
	public int getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}
	public List<SelectItem> getQuotationIdList() {
		return quotationIdList;
	}
	public void setQuotationIdList(List<SelectItem> quotationIdList) {
		this.quotationIdList = quotationIdList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public EnquiryQuotationTO getEnquiryQuotationTO() {
		return enquiryQuotationTO;
	}


	public void setEnquiryQuotationTO(EnquiryQuotationTO enquiryQuotationTO) {
		this.enquiryQuotationTO = enquiryQuotationTO;
	}

	/**
	 * This function calls accept booking method which in turns
	 * return a string based on which navigation of next
	 * page is decided.
	 * @param null
	 * @return String
	 * 
	 */
	public String acceptBooking()
	{

		AutoSellWrapper autoSellWrapper = new AutoSellWrapper ();

		try {
			autoSellWrapper.acceptBooking(this.quotationId);
			return "success";
		} 
		catch (Exception e)
		{
			this.setMessage(e.getMessage());
			return "failure";
		}
	}

	/**
	 * This function calls refuse booking method which in turns
	 * return a string based on which navigation of next
	 * page is decided.
	 * @param null
	 * @return String
	 * 
	 */
	public String refuseBooking()
	{
		AutoSellWrapper autoSellWrapper = new AutoSellWrapper ();

		try {
			autoSellWrapper.refuseBooking(this.quotationId);
			return "success";
		} 
		catch (Exception e)
		{
			this.setMessage(e.getMessage());
			return "failure";
		}
	}

	/**
	 * This function is a event handler method which
	 * accept a ValueChangeEvent and based on it produces 
	 * a specific response.
	 * @param ValueChangeEvent
	 * @return void
	 * 
	 */
	public void getQuotationDetails(ValueChangeEvent event)
	{    

		this.quotationId=(Integer)event.getNewValue();

		Iterator<EnquiryQuotationTO> enquiryIter = enquiryQuotaionTOList.iterator();
		while(enquiryIter.hasNext())
		{
			EnquiryQuotationTO enquiryQuotationTO = enquiryIter.next();

			if(enquiryQuotationTO.getQuotaionTO().getQuotationId()==this.quotationId)
				this.setEnquiryQuotationTO(enquiryQuotationTO);
		}

	}


}



