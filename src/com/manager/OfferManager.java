package com.manager;

import java.util.List;
import com.TO.OfferTO;
import com.exception.InvalidOfferIdException;
import com.service.OfferService;

public class OfferManager {

	public OfferTO getOffer(Integer offerId)throws InvalidOfferIdException 
	{
		/*Instantiating Object Of Offer Service
		  and calling appropriate method*/
		OfferService offerService=new OfferService();
		return offerService.getOffer(offerId);
	}

	public List<OfferTO> getOffers() throws Exception
	{
		/*Instantiating Object Of Offer Service
		  and calling appropriate method*/
		OfferService offerService=new OfferService();
		return offerService.getOffers();
	}



}
