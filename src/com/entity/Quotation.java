package com.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Quotation {
	@Id
	@SequenceGenerator(name="seq_QuotationId", sequenceName="seq_QuotationId",initialValue=12004,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_QuotationId")
	private int quotationId;
	private int requestId;
	private double actualPrice;
	private int offerId;
	private double quotedPrice;
	private char status;
	@Temporal(TemporalType.DATE)
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
