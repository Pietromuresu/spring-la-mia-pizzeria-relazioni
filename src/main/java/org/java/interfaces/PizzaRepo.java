package org.java.interfaces;

import java.util.List;

import org.java.POJO.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepo extends JpaRepository<Pizza, Long> {

	
	public List<Pizza> findByNameContaining(String name);
}
