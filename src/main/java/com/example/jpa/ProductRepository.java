package com.example.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {

//	List<Product> findByPriceGreaterThan(double price);
//
//	boolean existsByName(String name);

}
