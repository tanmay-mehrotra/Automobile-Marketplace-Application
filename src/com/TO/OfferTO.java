package com.TO;

import java.util.Date;

public class OfferTO {
			
		private int offerId;
		private String offerName;
		private int discount;
		private Date startDate;
		private Date endDate;
		private char status;
		public int getOfferId() {
			return offerId;
		}
		public void setOfferId(int offerId) {
			this.offerId = offerId;
		}
		public String getOfferName() {
			return offerName;
		}
		public void setOfferName(String offerName) {
			this.offerName = offerName;
		}
		public int getDiscount() {
			return discount;
		}
		public void setDiscount(int discount) {
			this.discount = discount;
		}
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		public char getStatus() {
			return status;
		}
		public void setStatus(char status) {
			this.status = status;
		}

}
