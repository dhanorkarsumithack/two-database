package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sumit.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	public Customer findByName(String name);
}
