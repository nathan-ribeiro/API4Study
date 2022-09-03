package com.study.storage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.study.storage.model.Product;
import com.study.storage.repository.ProductsRepository;

@RestController
@RequestMapping("/storage")
public class StorageController {

	@Autowired
	private ProductsRepository repository;
	
	@GetMapping("/check")
	public String verify() {
		return "Working very well";
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		return repository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product addNewProduct(@RequestBody Product product) {
		
		System.out.println("DESC: " + product.getDesc());
		
		return repository.save(product);
	}
}
