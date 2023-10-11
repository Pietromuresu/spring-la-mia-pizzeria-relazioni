package org.java.interfaces;

import org.java.POJO.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepo extends JpaRepository<Offer, Long>{

}
