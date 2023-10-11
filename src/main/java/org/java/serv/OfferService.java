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

}
