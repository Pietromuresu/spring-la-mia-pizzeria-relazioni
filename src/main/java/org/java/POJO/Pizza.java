package org.java.POJO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pizza")
public class Pizza {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(length=100, nullable = false)
	@Length(min = 3, max = 100, message= "il nome deve avere un minimo di 3 caratteri ed un massimo di 100")
	private String name;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	@Length(min = 5, max = 65535, message= "La descrizione deve avere un minimo di 5 caratteri ")
	private String description;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	@Length(min = 3, max = 100, message= "Devi inserire almeno una foto")
	private String fotoUrl;
	
	@Column(nullable = false)
	@NotNull(message="E' necessario inserire anche il prezzo")
	@Min(value=1, message= "La pizza non può essere gratis" )
	private BigDecimal price;
	
	
	@OneToMany(mappedBy= "pizza")
	private List<Offer> offers;
	
	@ManyToMany()
	private List<Ingredient> ingredients;
	

	public Pizza() {}
	public Pizza(String name, String description, String fotoUrl, BigDecimal price, Ingredient... ingredient) {
		
		setDescription(description);
		setFotoUrl(fotoUrl);
		setId(id);
		setName(name);
		setPrice(price);
		setIngredients(Arrays.asList(ingredient));

	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getFotoUrl() {

		return fotoUrl;
	}
	public void setFotoUrl(String fotoUrl) {
		
		this.fotoUrl = fotoUrl;
	}
	
	public String getFullFotoUrl(String fotoUrl) {
		String baseUrl = "/imgs/";
		
		
		return baseUrl + fotoUrl;
	}
	


	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public boolean hasIngredient(Ingredient ingredient) {
			
		if (getIngredients() == null) return false;
				
			for ( Ingredient ing : getIngredients()) 
				if (ingredient.getId() == ing.getId())
					return true;
			
		
		return false;
	}
	
	@Override
	public String toString() {
		return "[" + id + "]" + name + " \n"
				+ description;
	}
	
}
