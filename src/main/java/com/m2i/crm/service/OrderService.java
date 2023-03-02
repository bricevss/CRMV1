package com.m2i.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2i.crm.entity.Order;
import com.m2i.crm.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository oRepo;
	

	public List<Order> getAllOrders(){	
		return oRepo.findAll();
	}

	public Order getById(int id) {
		return oRepo.findById(id).orElse(null);
	}
	
	public void delete(int id) {
		
		Order o = oRepo.findById(id).orElse(null);
		
		if(o != null) {
			oRepo.delete(o);
		}				
	}
	
	public void addOrder(Order o) {
		oRepo.save(o);
	}

	public void update(int id, Order o) {
		Order order = oRepo.findById(id).orElse(null);
		
		if (order != null) {
			
			order.setTypePresta(o.getTypePresta());
			order.setDesignation(o.getDesignation());
			order.setNbDays(o.getNbDays());
			order.setUnitPrice(o.getUnitPrice());
			order.setState(o.getState());
			
			oRepo.save(o);
		}
		
		
	}

}
