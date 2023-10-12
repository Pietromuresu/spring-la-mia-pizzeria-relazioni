package org.java.POJO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="offer")
public class Offer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull(message="E' necessario inserire la data di inizio")
	private LocalDate startDate;

	@NotNull(message="E' necessario inserire la data di fine")
	private LocalDate endDate;

	
	@NotNull(message="E' necessario inserire il titolo")
	@Length(min = 3, max=255, message= "il titolo deve avere un minimo di 3 caratteri ")
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
	
	public String getHtmlStartDate() {
		return startDate == null
				? null
				: startDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
	
	public String getHtmlEndDate() {
		return endDate == null
				? null
				: endDate.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}
	
	public void setHtmlEndDate(String date) {
		setEndDate(LocalDate.parse(date));
	}
	public void setHtmlStartDate(String date) {
		setStartDate(LocalDate.parse(date));
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
