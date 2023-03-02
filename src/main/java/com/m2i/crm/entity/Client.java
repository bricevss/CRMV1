package com.m2i.crm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name="client")
public class Client {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min = 2, max =50, message = "companyName must be between 2 and 50 characters")
    @NotNull(message = "companyName cannot be null")   
	private String companyName;
    
    @Size(min = 3, max =50, message = "firstName must be between 3 and 50 characters")
    @NotNull(message = "firstName cannot be null")
	private String firstName;
    
    @Size(min = 3, max =50, message = "lastName must be between 3 and 50 characters")
    @NotNull(message = "lastName cannot be null")
	private String lastName;
    
    @Email(message = "Email should be valid")
    @NotNull(message = "email cannot be null")
	private String email;
    
    @NotNull(message = "phone cannot be null")
	private String phone;
    
    @NotNull(message = "address cannot be null")
	private String address;
    
    @Length(min = 5, max = 10, message = "zipCode length must be between 5 and 10 digits")
    @NotNull(message = "zipCode cannot be null")
	private String zipCode;
    
    @NotNull(message = "city cannot be null")
	private String city;
    
    @Size(min = 3, max =50, message = "lastName must be between 3 and 50 characters")
    @NotNull(message = "country cannot be null")
	private String country;
    
    @Min(value = 0, message = "state should not be less than 0")
    @Max(value = 1, message = "state should not be greater than 1")
    @NotNull(message = "state cannot be null")
	private int state;
	
	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_oc", referencedColumnName = "id")
	private List<Order> orders;
	
    public Client(Faker f) {
    	this.companyName = f.app().name();
    	this.firstName = f.name().firstName();
    	this.lastName = f.name().lastName();
    	this.email = f.internet().emailAddress();
    	this.phone = f.phoneNumber().phoneNumber();
    	this.address = f.address().buildingNumber();
    	this.zipCode = f.address().zipCode();
    	this.city = f.address().city();
    	this.country = f.address().country();
    	this.state = f.number().numberBetween(0, 1);
    	
    	this.orders = new ArrayList<>();
   	 
    }	
	
}
