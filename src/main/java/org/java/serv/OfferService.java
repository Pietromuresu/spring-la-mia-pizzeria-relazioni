package org.java.serv;

import java.util.List;

import org.java.POJO.Offer;
import org.java.interfaces.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
	

	@Autowired
	private OfferRepo offerRepo;
	
	public void save(Offer offer) {
		offerRepo.save(offer);
	}
	
	public List<Offer> findAll() {
		return offerRepo.findAll();
	}
	
	public Offer findById(Long id) {
		
		return offerRepo.findById(id).get();
	}
	public void deleteById(Long id) {
		
		offerRepo.deleteById(id);
	}
	
	public void updatePizza(Offer offer) {
		
		Long id = offer.getId();
		Offer offerToUpdate = offerRepo.findById(id).get();

	
		
		offerRepo.save(offerToUpdate);
	}

}
