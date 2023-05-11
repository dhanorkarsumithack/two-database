package com.sumit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.entities.Customer;
import com.sumit.repository.CustomerRepo;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerRepo customerRepo;
	
	
	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}
	
	public List<Customer> findAllCustomer() {
		return customerRepo.findAll();
	}
	
	public Customer findCustomerByName(String name) {
		return customerRepo.findByName(name);
	}
	
	
}
