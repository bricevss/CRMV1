package com.m2i.crm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name="orders")
public class Order {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Length(min = 5, max = 100, message = "typePresta length must be between 5 and 100 characters")
	@NotNull(message = "typePresta cannot be null")
	private String typePresta;
	
	@Length(min = 5, max = 50, message = "designation length must be between 5 and 50 characters")
	@NotNull(message = "designation cannot be null")
	private String designation;
	
	@Min(value = 1, message = "nbDays cannot be less then a day")
	@Max(value = 1825, message = "nbDays cannot exceed 1825 days")
	@NotNull(message = "nbDays cannot be null")
	private int nbDays;
	
	@Min(value = 1, message = "unitPrice cannot be less then 1")
	@Max(value = 100000, message = "unitPrice cannot exceed 100000")
	@NotNull(message = "unitPrice cannot be null")
	private int unitPrice;
	
    @Min(value = 0, message = "state should not be less than 0")
    @Max(value = 2, message = "state should not be greater than 2")
    @NotNull(message = "state cannot be null")
	private int state;
	
	@ManyToOne
	@JsonIgnore
	private Client client;
	
    public Order(Faker f) {
    	this.typePresta = f.lorem().characters(10);
    	this.designation = f.name().name();
    	this.nbDays = f.number().numberBetween(1, 1825);
    	this.unitPrice = f.number().numberBetween(1, 100000);
    	this.state = f.number().numberBetween(0, 2);
   	 
    }

}
