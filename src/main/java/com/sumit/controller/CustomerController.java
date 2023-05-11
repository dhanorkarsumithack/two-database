package com.sumit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumit.entities.Customer;
import com.sumit.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
	public Customer savecustomer(@RequestBody Customer customer) {
		return customerService.saveCustomer(customer);
		
	}
	
	@GetMapping("/get")
	public List<Customer> getAllCustomer(){
		return customerService.findAllCustomer();
	}
	
	@GetMapping("/getByName/{name}")
	public Customer getCustomerByName(@PathVariable("name") String name) {
		return customerService.findCustomerByName(name);
	}
	
}
