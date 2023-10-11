package org.java.POJO;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate startDate;
	private LocalDate endDate;
	private String title;
	
	@ManyToOne
	@JoinColumn(name="pizza_id", nullable = false)
	private Pizza pizza;

	
	public Offer() {};
	public Offer(String title, LocalDate startDate, LocalDate endDate, Pizza pizza) {
		
		setEndDate(endDate);
		setStartDate(startDate);
		setId(id);
		setTitle(title);
		setPizza(pizza);
	}
	
	
	// GETTER AND SETTERS

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Start: " + startDate  +
				"/nEnd: " + endDate  + 
				"/n Title: " + title;
		
		
	}
	
	
}
