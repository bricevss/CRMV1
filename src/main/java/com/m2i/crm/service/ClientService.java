package com.m2i.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.crm.entity.Client;
import com.m2i.crm.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository cRepo;
	
	public List<Client> getAllClients(){	
		return cRepo.findAll();
	}

	public Client getById(int id) {
		return cRepo.findById(id).orElse(null);
	}
	
	public void delete(int id) {
		
		Client c = cRepo.findById(id).orElse(null);
		
		if(c != null) {
			cRepo.delete(c);
		}				
	}
	
	public void addClient(Client c) {
		cRepo.save(c);
	}

	public void update(int id, Client c) {
		Client client = cRepo.findById(id).orElse(null);
		
		if (client != null) {
			
			client.setCompanyName(c.getCompanyName());
			client.setFirstName(c.getFirstName());
			client.setLastName(c.getLastName());
			client.setEmail(c.getEmail());
			client.setPhone(c.getPhone());
			client.setAddress(c.getAddress());
			client.setZipCode(c.getZipCode());
			client.setCity(c.getCity());
			client.setCountry(c.getCountry());
			client.setState(c.getState());		
			
			cRepo.save(c);
		}
		
		
	}

}
