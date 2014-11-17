package com.manager;

import com.TO.QuotationTO;
import com.exception.InvalidQuotationIdException;
import com.service.QuotationService;

public class QuotationManager {

	public QuotationTO updateQuotationStatus(int quotationId,char bookingStatus)throws Exception
	{
		QuotationService quotationService=new QuotationService();
		QuotationTO quotationTO=quotationService.updateQuotationStatus(quotationId,bookingStatus);
		return quotationTO;	
	}
	public QuotationTO generateQuotation(QuotationTO quotationTO)throws Exception {
		/*Instantiating Object Of Quotation Service
		  and calling appropriate method*/
		QuotationService quotationService=new QuotationService();
		return quotationService.generateQuotation(quotationTO);

	}
	public QuotationTO updateQuotation(QuotationTO quotationTO)
	throws InvalidQuotationIdException{
		/*Instantiating Object Of Quotation Service
		  and calling appropriate method*/
		QuotationTO quotationTO1=new QuotationService().updateQuotation(quotationTO);
		return quotationTO1;
	}

}
