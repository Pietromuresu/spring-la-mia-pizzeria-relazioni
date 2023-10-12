package org.java;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.java.POJO.Ingredient;
import org.java.POJO.Offer;
import org.java.POJO.Pizza;
import org.java.interfaces.IngredientRepo;
import org.java.interfaces.OfferRepo;
import org.java.interfaces.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaRelazioniApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaRepo pizzaRepo;
	
	@Autowired
	private OfferRepo offerRepo;

	@Autowired
	private IngredientRepo ingredientRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaRelazioniApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingredient pomodoro = new Ingredient("pomodoro");
		Ingredient mozzarella = new Ingredient("mozzarella");
		Ingredient origano = new Ingredient("origano");
		Ingredient patate = new Ingredient("patate");
		
		ingredientRepo.save(pomodoro);
		ingredientRepo.save(mozzarella);
		ingredientRepo.save(origano);
		ingredientRepo.save(patate);
		
		
		Pizza margherita = new Pizza("margherita", "pom., mozz., basilico", "margherita.webp", new BigDecimal(4.5), pomodoro, mozzarella);
		Pizza ortolana = new Pizza("ortolana", "pom., mozz., verdure grigliate miste", "ortolana.jpeg", new BigDecimal(4.5), pomodoro, mozzarella);
		Pizza diavola = new Pizza("diavola", "pom., mozz., salsiccia secca piccante, olio piccante", "diavola.jpeg", new BigDecimal(7.5), pomodoro, origano);
		Pizza bufala = new Pizza("bufala", "pom., mozzarella di bufala, basilico", "bufala.jpeg", new BigDecimal(9), pomodoro, mozzarella);
		Pizza marinara = new Pizza("marinara", "pom., aglio, origano, acciughe", "marinara.jpeg", new BigDecimal(7), pomodoro, patate);
	
		pizzaRepo.save(margherita);
		pizzaRepo.save(ortolana);
		pizzaRepo.save(diavola);
		pizzaRepo.save(bufala);
		pizzaRepo.save(marinara);
		
		Offer offer1 = new Offer("nuovo1", LocalDate.parse("2023-01-20"), LocalDate.parse("2023-01-30"), marinara);
		offerRepo.save(offer1);
		Offer offer2 = new Offer("nuovo1", LocalDate.parse("2022-10-20"), LocalDate.parse("2023-03-13"), ortolana);
		offerRepo.save(offer2);
		Offer offer3 = new Offer("nuovo1", LocalDate.parse("2023-08-20"), LocalDate.parse("2023-05-30"), diavola);
		offerRepo.save(offer3);
		Offer offer4 = new Offer("nuovo1", LocalDate.parse("2023-01-20"), LocalDate.parse("2023-11-10"), ortolana);
		offerRepo.save(offer4);
		Offer offer5 = new Offer("nuovo1", LocalDate.parse("2023-01-20"), LocalDate.parse("2023-01-30"), margherita);
		offerRepo.save(offer5);
		
//		margherita.setName("margherita2.0");
//		pizzaService.updatePizza(margherita);
//		
//		pizzaService.deleteById(marinara.getId());
	
	}
	
	

}
