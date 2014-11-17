package com.bean;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.wrapper.AutoSellWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import com.TO.EnquiryQuotationTO;
import com.TO.ModelTO;
import com.TO.OfferTO;
import com.TO.ProspectiveCustomerTO;
import com.TO.QuotationTO;
import com.exception.InvalidModelIdException;
import com.exception.InvalidProspectiveCustomerIdException;
import com.exception.InvalidQuotationIdException;
import com.exception.InvalidRequestIdException;
import com.exception.NoOffersAvailableException;



public class UpdateQuotationMB {
	private Integer offerId;
	private List<SelectItem> offerIdList;
	private EnquiryQuotationTO enquiryQuotationTO;
	private Double discountedPrice;
	private String message;


	/**
	 * @return the offerId
	 */
	public Integer getOfferId() {
		return offerId;
	}
	/**
	 * @param offerId the offerId to set
	 */
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	/**
	 * @return the offerIdList
	 */
	public List<SelectItem> getOfferIdList() {
		return offerIdList;
	}
	/**
	 * @param offerIdList the offerIdList to set
	 */
	public void setOfferIdList(List<SelectItem> offerIdList) {
		this.offerIdList = offerIdList;
	}
	/**
	 * @return the enquiryQuotationTO
	 */
	public EnquiryQuotationTO getEnquiryQuotationTO() {
		return enquiryQuotationTO;
	}
	/**
	 * @param enquiryQuotationTO the enquiryQuotationTO to set
	 */
	public void setEnquiryQuotationTO(EnquiryQuotationTO enquiryQuotationTO) {
		this.enquiryQuotationTO = enquiryQuotationTO;
	}
	/**
	 * @return the discountedPrice
	 */
	public Double getDiscountedPrice() {
		return discountedPrice;
	}
	/**
	 * @param discountedPrice the discountedPrice to set
	 */
	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
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
	public UpdateQuotationMB() throws Exception{
		try{
			Integer requestId;
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext etx = ctx.getExternalContext();
			HttpSession session = (HttpSession)etx.getSession(true);
			requestId=(Integer) session.getAttribute("requestId");
			this.offerIdList=new ArrayList<SelectItem>();
			enquiryQuotationTO=new AutoSellWrapper().getEnquiryQuotationDetails(requestId);
			List<OfferTO> offerToList=enquiryQuotationTO.getOfferTOList();
			Iterator<OfferTO> Iter=offerToList.iterator();
			while(Iter.hasNext()){
				OfferTO offer = Iter.next();
				SelectItem item = new SelectItem(offer.getOfferId(),offer.getOfferName());
				offerIdList.add(item);
			}
		}

		catch(NoOffersAvailableException e){
			this.setMessage(e.getMessage());
		}
		catch(InvalidProspectiveCustomerIdException e){
			this.setMessage(e.getMessage());
		}
		catch(InvalidModelIdException e){
			this.setMessage(e.getMessage());
		}
		catch(InvalidRequestIdException e){
			this.setMessage(e.getMessage());
		}

	}


	public void priceAfterDiscount(ValueChangeEvent event){

		Integer discount=null;
		offerId=(Integer)event.getNewValue();

		if(offerId==0){

			this.discountedPrice=null;
			this.message="Please Select an Offer from the menu";
		}
		else{


			List<OfferTO> offerTO=this.enquiryQuotationTO.getOfferTOList();
			ModelTO modelTO=this.enquiryQuotationTO.getModelTO();
			Iterator<OfferTO> offerIter=offerTO.iterator();
			while(offerIter.hasNext()){
				OfferTO offer = offerIter.next();
				if(offer.getOfferId()==offerId){
					discount=offer.getDiscount();
				}
			}
			this.discountedPrice=(Double)modelTO.getPrice()-((discount/100.0)*modelTO.getPrice());
			this.message=null;
		}

	}


	public String updateQuotation(){

		if(offerId==0){
			return "fail";

		}
		QuotationTO quotationTO=this.enquiryQuotationTO.getQuotaionTO();
		quotationTO.setQuotedPrice(discountedPrice);
		quotationTO.setOfferId(offerId);
		try
		{
			quotationTO=new AutoSellWrapper().updateQuotation(quotationTO);

		}
		catch(InvalidQuotationIdException e)
		{
			this.setMessage(e.getMessage());
			return "fail";
		}

		catch(Exception e)
		{
			this.setMessage(e.getMessage());
			return "fail";

		}
		this.message=null;
		return "success";
	}
	public String sendQuotation()
	{
		ProspectiveCustomerTO prospectiveCustomerTO=this.enquiryQuotationTO.getProspectiveCustomerTO();
		this.setMessage("Quotation has been sent to "+prospectiveCustomerTO.getCustomerName());

		return "success";
	}


}
