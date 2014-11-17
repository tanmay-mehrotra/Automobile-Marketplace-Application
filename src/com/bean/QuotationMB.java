package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import com.TO.EnquiryDetailsTO;
import com.TO.OfferTO;
import com.TO.QuotationTO;
import com.exception.InvalidModelIdException;
import com.exception.InvalidOfferIdException;
import com.exception.InvalidProspectiveCustomerIdException;
import com.exception.InvalidRequestIdException;
import com.exception.NoOffersAvailableException;
import com.wrapper.AutoSellWrapper;

public class QuotationMB {
	private Integer offerId;
	private List<SelectItem> offerIdList;
	private EnquiryDetailsTO enquiryDetailsTO;
	private Double priceAfterDiscount;
	private String message;

	//Constructor for populating the respective list at startup
	public QuotationMB() throws Exception
	{
		//Instantiating the wrapper Class object
		AutoSellWrapper wrapper=new AutoSellWrapper();
		//Retreiving attribute requestId from session 
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		Integer requestId=(Integer)session.getAttribute("requestId");
		//initializing the references
		this.offerIdList=new ArrayList<SelectItem>();
		List<OfferTO> listOfferTO=new ArrayList<OfferTO>();

		try
		{	
			/*calling the getNewEnquiry method of the wrapper function 
			to populate the EnquiryDetailsTO List*/
			this.enquiryDetailsTO=wrapper.getEnquiryDetails(requestId);
			listOfferTO=wrapper.getOffers();
			Iterator<OfferTO> offerIter = listOfferTO.iterator();
			//Generating a list of type select items to display the available offers		
			while(offerIter.hasNext()){
				OfferTO offerTO= offerIter.next();
				SelectItem selectItem=new SelectItem(offerTO.getOfferId(),offerTO.getOfferName());
				this.offerIdList.add(selectItem);
			}

		}
		//Catching Exceptions
		catch(InvalidRequestIdException e)
		{
			this.message=e.getMessage();
		}
		catch(InvalidModelIdException e)
		{
			this.message=e.getMessage();
		}
		catch(InvalidProspectiveCustomerIdException e)
		{
			this.message=e.getMessage();
		}
		catch(NoOffersAvailableException e)
		{
			this.message=e.getMessage();
		}
		

	}
	//Getters and Setters for the instance variables
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	public List<SelectItem> getOfferIdList() {
		return offerIdList;
	}
	public void setOfferIdList(List<SelectItem> offerIdList) {
		this.offerIdList = offerIdList;
	}
	public EnquiryDetailsTO getEnquiryDetailsTO() {
		return enquiryDetailsTO;
	}
	public void setEnquiryDetailsTO(EnquiryDetailsTO enquiryDetailsTO) {
		this.enquiryDetailsTO = enquiryDetailsTO;
	}
	public Double getPriceAfterDiscount() {
		return priceAfterDiscount;
	}
	public void setPriceAfterDiscount(Double priceAfterDiscount) {
		this.priceAfterDiscount = priceAfterDiscount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	/*Value Change event to display the price after selecting a particular model*/
	public void priceAfterDiscount1(ValueChangeEvent event)
	{
		//initializing the references
		OfferTO offerTO=null,tempOfferTO=null;
		int flag=0;
		List<OfferTO> listOfferTO;

		//Getting the new value from value Change event
		this.offerId=(Integer)event.getNewValue();
		try
		{
			/*calling the getNewEnquiry method of the wrapper function 
			to populate the OfferId List*/
			for (int count = 0;count<this.enquiryDetailsTO.getOfferTOList().size();count++)
			{
				listOfferTO=this.enquiryDetailsTO.getOfferTOList();
				tempOfferTO=listOfferTO.get(count);
				if(offerId==tempOfferTO.getOfferId())
				{
					offerTO=tempOfferTO;
					flag=1;
				}

			}

			if(flag==0)
				throw new InvalidOfferIdException();
		}
		//Catching Exceptions
		catch(InvalidOfferIdException e)
		{
			this.setMessage(e.getMessage());

		}
		//Setting the price after discount
		int discount=offerTO.getDiscount();
		this.priceAfterDiscount=(this.enquiryDetailsTO.getModelTO().getPrice())*(100.0-discount)/100.0;

	}

	//Function to generate quotation and persist it in database
	public String generateQuotation() throws Exception
	{
		AutoSellWrapper wrapper=new AutoSellWrapper();
		QuotationTO quotationTO=new QuotationTO();
		//retreiving session attribute requestId 
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession)etx.getSession(true);
		Integer requestId=(Integer)session.getAttribute("requestId");
		
		//Setting the quotationTO object
		quotationTO.setActualPrice(this.enquiryDetailsTO.getModelTO().getPrice());
		quotationTO.setOfferId(this.offerId);
		quotationTO.setQuotedPrice(this.priceAfterDiscount);
		quotationTO.setDateGenerated(new Date());
		quotationTO.setRequestId(requestId);
		quotationTO.setStatus('F');
		try
		{
			//Calling wrapper by passing quotationTO
			quotationTO=wrapper.generateQuotation(quotationTO);
			session.setAttribute("quotationId",quotationTO.getQuotationId());
			return "success";
		}
		catch(InvalidOfferIdException e)
		{
			this.setMessage(e.getMessage());
			return "fail";
		}
		catch(InvalidRequestIdException e)
		{
			this.setMessage(e.getMessage());
			return "fail";
		}

	}
	public String sendQuotation()
	{
		this.setMessage("Quotation Sent Successfully to Mr."+this.enquiryDetailsTO.getProspectiveCustomerTO().getCustomerName());
		return message;
	}
}
