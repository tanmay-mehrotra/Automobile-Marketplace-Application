package com.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.TO.OfferTO;
import com.entity.Offer;
import com.exception.InvalidOfferIdException;
import com.exception.NoOffersAvailableException;

public class OfferService {
	EntityManager em=null;

	@SuppressWarnings("unchecked")
	public List<OfferTO> getOffers()throws NoOffersAvailableException{

		List rs;
		List<OfferTO> listOfferTO=new ArrayList<OfferTO>();
		EntityManager em=null;
		Offer offer;
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
		em=emf.createEntityManager();
		Query query=em.createQuery("Select o from Offer o");
		rs=query.getResultList();
		if(rs.size()==0)
			throw new NoOffersAvailableException();

		for (int count = 0;count<rs.size();count++)
		{
			OfferTO offerTO=new OfferTO();
			offer=(Offer) rs.get(count);
			offerTO.setDiscount(offer.getDiscount());
			offerTO.setEndDate(offer.getEndDate());
			offerTO.setOfferId(offer.getOfferId());
			offerTO.setOfferName(offer.getOfferName());
			offerTO.setStartDate(offer.getStartDate());
			offerTO.setStatus(offer.getStatus());
			listOfferTO.add(offerTO);

		}
		em.close();
		return listOfferTO;			

	}
	public OfferTO getOffer(Integer offerId)throws InvalidOfferIdException {


		EntityManager em=null;
		OfferTO offerTO=new OfferTO();
		try
		{

			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
			em=emf.createEntityManager();

			Offer offer=em.find(Offer.class, offerId);
			if (offer==null)
				throw new InvalidOfferIdException();
			offerTO.setDiscount(offer.getDiscount());
			offerTO.setEndDate(offer.getEndDate());
			offerTO.setOfferId(offer.getOfferId());
			offerTO.setOfferName(offer.getOfferName());
			offerTO.setStartDate(offer.getStartDate());
			offerTO.setStatus(offer.getStatus());
			return offerTO ;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}

}
