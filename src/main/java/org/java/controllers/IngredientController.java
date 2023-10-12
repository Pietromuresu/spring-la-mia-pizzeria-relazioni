package org.java.controllers;

import java.util.List;

import org.java.POJO.Ingredient;
import org.java.POJO.Pizza;
import org.java.serv.IngredientService;
import org.java.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientServ;
	
	@Autowired
	private PizzaService pizzaServ;
	
	@GetMapping
	public String getIngredientsIndex(Model model) {
		
		List<Ingredient> ingredients = ingredientServ.findAll();
		Ingredient ingredient = new Ingredient();
		
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("ingredientToAdd", ingredient);
		
		return "create-ingredient";
		
	}
	
	@PostMapping("/add")
	public String saveIngredient(Model model,
			@ModelAttribute @Valid Ingredient ingredient, 
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {

			return "create-offer";
		} 
	
		ingredientServ.save(ingredient);		
	
		
		return "redirect:/ingredients";	
	}
	
	@PostMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable String id) {
		
		List<Pizza> pizzas = pizzaServ.findAll();
		Ingredient ingredient = ingredientServ.findById(Long.valueOf(id));
		
		for(Pizza pizza : pizzas) { 
			if(pizza.getIngredients().contains(ingredient)) pizza.getIngredients().remove(ingredient);
		}
		
		ingredientServ.deleteById(Long.valueOf(id));
		
		return "redirect:/ingredients";	
	}
	
	
}
