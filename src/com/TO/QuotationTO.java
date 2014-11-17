package com.TO;

import java.util.Date;

public class QuotationTO {
  
	private int quotationId;
    private int requestId;
    private double actualPrice;
    private int offerId;
    private double quotedPrice;
    private char status;
    private Date dateGenerated;
	public int getQuotationId() {
		return quotationId;
	}
	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public double getQuotedPrice() {
		return quotedPrice;
	}
	public void setQuotedPrice(double quotedPrice) {
		this.quotedPrice = quotedPrice;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Date getDateGenerated() {
		return dateGenerated;
	}
	public void setDateGenerated(Date dateGenerated) {
		this.dateGenerated = dateGenerated;
	}
    
    

}
