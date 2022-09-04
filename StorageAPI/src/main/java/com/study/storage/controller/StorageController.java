package com.study.storage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.study.storage.exception.ProductNotFoundException;
import com.study.storage.model.Product;
import com.study.storage.service.StorageProductsService;

@RestController
@RequestMapping("/storage")
public class StorageController {

	@Autowired
	private StorageProductsService service;
	
	private static final String MESSAGE_ERROR_UPDATE = "Não foi possivel alterar o produto";
	private static final String MESSAGE_ERROR_DELETE = "Não foi possivel deletar o produto";
	private static final String MESSAGE_ERROR_ADD = "Não foi possivel salvar o produto";
	
	@GetMapping("/check")
	public String verify() {
		return "Working very well";
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		
		return service.getAllProducts();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> addNewProduct(@RequestBody Product product) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.createNewProduct(product));
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MESSAGE_ERROR_ADD);
		}
		
	}
	
	@PutMapping
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.updateProduct(product));
		} catch(ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MESSAGE_ERROR_UPDATE);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") final long id) {
		
		try {
			System.out.println("ID: " + id);
			
			return ResponseEntity.status(HttpStatus.OK).body(service.deleteProduct(id));
		} catch(ProductNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			System.out.println("ERRO: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MESSAGE_ERROR_DELETE);
		}
		
	}
	
}
