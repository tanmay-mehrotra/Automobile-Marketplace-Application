package com.TO;

import java.util.List;

public class EnquiryDetailsTO {
	private ModelTO modelTO;
	private ProspectiveCustomerTO prospectiveCustomerTO;
	List<OfferTO> offerTOList;
	
	public ModelTO getModelTO() {
		return modelTO;
	}
	public void setModelTO(ModelTO modelTO) {
		this.modelTO = modelTO;
	}
	
	public ProspectiveCustomerTO getProspectiveCustomerTO() {
		return prospectiveCustomerTO;
	}
	public void setProspectiveCustomerTO(ProspectiveCustomerTO prospectiveCustomerTO) {
		this.prospectiveCustomerTO = prospectiveCustomerTO;
	}
	public List<OfferTO> getOfferTOList() {
		return offerTOList;
	}
	public void setOfferTOList(List<OfferTO> offerTOList) {
		this.offerTOList = offerTOList;
	}
	
	
	

}
