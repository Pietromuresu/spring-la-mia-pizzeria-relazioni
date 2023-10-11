package org.java.controllers;

import org.java.POJO.Offer;
import org.java.POJO.Pizza;
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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OfferController {

	@Autowired
	private OfferService offerServ;
	
	@Autowired
	private PizzaService pizzaServ;
	
	@PostMapping("/create/{pizza_id}")
	public String store( Model model, 
						@Valid @ModelAttribute Offer offer,
						@PathVariable("pizza_id") int id, 
						BindingResult bindingResult
						) {
		
		Pizza pizza = pizzaServ.findById(Long.valueOf(id));
		offer.setPizza(pizza);
		
		if(bindingResult.hasErrors()) {
			return "create-offer";
		}
		
		offerServ.save(offer);
		
		
		return "redirect:/pizza/" + id;
		
	}
	
	@GetMapping("/create/{id}")
	public String createForm(Model model, @PathVariable Long id) {
		
		
		model.addAttribute("offer", new Offer());
		
		return "create-offer";
	}
	
	
	@PostMapping("/update/{pizza_id}/{id}")
	public String updateoffer( Model model, 
			@Valid @ModelAttribute Offer offer,
			@PathVariable("pizza_id") int id, 
			BindingResult bindingResult
			) {
		
		Pizza pizza = pizzaServ.findById(Long.valueOf(id));
		offer.setPizza(pizza);
		
		if(bindingResult.hasErrors()) {
			return "create-offer";
		}else {
		
			offerServ.save(offer);
		}
		
		
		return "redirect:/pizza/" + id;
		
	}
	
	@GetMapping("/update/{pizza_id}/{id}")
	public String updateForm(Model model, 
			@PathVariable Long id) {
		
		Offer offerToUpdate = offerServ.findById(id);
		model.addAttribute("offer", offerToUpdate);
		
		return "create-offer";
	}
	
}
