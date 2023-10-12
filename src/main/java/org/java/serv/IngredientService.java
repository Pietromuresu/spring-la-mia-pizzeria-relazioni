package org.java.serv;

import java.util.List;

import org.java.POJO.Ingredient;
import org.java.interfaces.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
	

	@Autowired
	private IngredientRepo ingredientRepo;
	
	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	public void deleteById(Long id) {
		ingredientRepo.deleteById(id);
	}
	
	public List<Ingredient> findAll() {
		return ingredientRepo.findAll();
	}
	
	public Ingredient findById(Long id) {
		
		return ingredientRepo.findById(id).get();
	}
	
	public void updatePizza(Ingredient ingredient) {
		
		Long id = ingredient.getId();
		Ingredient offerToUpdate = ingredientRepo.findById(id).get();

	
		
		ingredientRepo.save(offerToUpdate);
	}

}
