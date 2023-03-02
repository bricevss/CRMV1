package com.m2i.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2i.crm.entity.Order;
import com.m2i.crm.repository.OrderRepository;
import com.m2i.crm.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderRepository oRepo;
	
	@Autowired
	OrderService oService;
	
	@GetMapping("/all")
	public List<Order> getAllClients(){
		return oService.getAllOrders();
	}
	
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable("id") int id){
		return oService.getById(id);
	}
	
    @PutMapping("/update/{id}")
    public void updateOrder(@PathVariable("id") int id, @RequestBody Order o) {
		
		oService.update(id, o);

    }
    
    @PostMapping("/create")
    public void createOrder (@RequestBody Order o) {
    	oService.addOrder(o);
    }
	
    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable("id") int id) {
    	
    	Order o = oRepo.findById(id).orElse(null);
		oRepo.delete(o);

    }

}
