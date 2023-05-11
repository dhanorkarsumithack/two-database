package com.sumit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.entities.Product;
import com.sumit.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public Product saveProduct(Product product) {
		
		return productRepo.save(product);
		
	}
	
	
	public List<Product> findAllProduct(){
		return productRepo.findAll();
	}
	
	public Product findProductByName(String name) {
		return productRepo.findByName(name);
	}
	
	
	
	
}
