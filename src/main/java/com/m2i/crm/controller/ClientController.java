package com.m2i.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.m2i.crm.entity.Client;
import com.m2i.crm.entity.Order;
import com.m2i.crm.repository.ClientRepository;
import com.m2i.crm.repository.OrderRepository;
import com.m2i.crm.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	ClientRepository cRepo;
	
	@Autowired
	OrderRepository oRepo;
	
	@Autowired
	ClientService cService;
	
	@PostMapping("/fake")
	public List<Client> fakeClients(){
		
		List<Client> clients = new ArrayList<>();
		Faker f = new Faker();
		Random r = new Random();
		
		for(int i = 0; i<50; i++) {
			
			Client client = new Client(f);
			cRepo.save(client);
			
			for(int j = 0; j<r.nextInt(10); j++) {
				
				Order o = new Order(f);
				client.getOrders().add(o);
				oRepo.save(o);
				
			}
			
		}
		
		return clients;
		
	}
	
	@GetMapping("/all")
	public List<Client> getAllClients(){
		return cService.getAllClients();
	}
	
	@GetMapping("/{id}")
	public Client getClientById(@PathVariable("id") int id){
		return cService.getById(id);
	}
	
    @PutMapping("/update/{id}")
    public void updateClient(@PathVariable("id") int id, @RequestBody Client c) {
		
		cService.update(id, c);

    }
    
    @PostMapping("/create")
    public void createClient (@RequestBody Client c) {
    	cService.addClient(c);
    }
	
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") int id) {
    	
		Client c = cRepo.findById(id).orElse(null);
		cRepo.delete(c);

    }

}
