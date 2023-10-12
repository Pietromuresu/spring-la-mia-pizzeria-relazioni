package org.java.controllers;

import java.util.List;

import org.java.POJO.Ingredient;
import org.java.POJO.Offer;
import org.java.POJO.Pizza;
import org.java.serv.IngredientService;
import org.java.serv.OfferService;
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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private PizzaService pizzaServ;
	
	@Autowired
	private IngredientService ingredientServ;
	
	@Autowired
	private OfferService offerServ;
	
	@GetMapping
	public String index(Model model) {
		
		List<Pizza> pizzas = pizzaServ.findAll();
		
		model.addAttribute("pizzas", pizzas);
		
		return "index";
	}
	
	@GetMapping("/create")
	public String createForm(Model model) {
		
		List<Ingredient> ingredients = ingredientServ.findAll();
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("allIngredients", ingredients);
		
		return "create";
	}
	
	@PostMapping("/create")
	public String store( Model model, 
						@ModelAttribute @Valid Pizza pizza, 
						BindingResult bindingResult
						) {
		return savePizza(model, pizza, bindingResult);
		
	}
	
	@GetMapping("/update/{id}")
	public String updatePizza(@PathVariable Long id , Model model) {
		
		List<Ingredient> ingredients = ingredientServ.findAll();
		Pizza pizzaToUpdate = pizzaServ.findById(id);

		model.addAttribute("allIngredients", ingredients);
		model.addAttribute("pizza", pizzaToUpdate);
		
		return "create";
	}
	
	@PostMapping("/update/{id}")
	public String update( Model model, 
						@ModelAttribute @Valid Pizza pizza, 
						BindingResult bindingResult
						) {
		
		return savePizza(model, pizza, bindingResult);
	}
	
	
	
	@RequestMapping("/name")
	public String getByName(Model model, 
							@RequestParam(required = false) String name) {
		List<Pizza> pizzas = null;
		
		String searched = name;
		
		if(name.isEmpty()) {
			
			pizzas = pizzaServ.findAll();
		}else {
			
			pizzas = pizzaServ.findByName(name); 
		}
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("searchedName", searched);
		
		return "index";
	}
	
	@GetMapping("/pizza/{id}")
	public String show(@PathVariable Long id, Model model) {
		
		Pizza pizza = pizzaServ.findById(id);
		
		model.addAttribute("pizza", pizza);
		
		return "show";
	}
	
	@PostMapping("/delete/{id}")
	public String deletePizza(@PathVariable("id") Long id) {
		
		Pizza pizza = pizzaServ.findById(id);
		for(Offer offer : pizza.getOffers()) {
			offerServ.deleteById(offer.getId());
		}
		
		pizzaServ.deleteById(id);

		return "redirect:/";
		
	}
	
	
	private String savePizza(Model model, 
			@ModelAttribute @Valid Pizza pizza, 
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			List<Ingredient> ingredients = ingredientServ.findAll();

			model.addAttribute("allIngredients", ingredients);
			return "create";
		} else {
			
			pizzaServ.save(pizza);
		}
		
		return "redirect:/";
	}
}
