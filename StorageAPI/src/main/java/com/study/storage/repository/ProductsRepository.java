package com.study.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.study.storage.model.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

	
	
}
