package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumit.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long >{

	public Product findByName(String name);
}
