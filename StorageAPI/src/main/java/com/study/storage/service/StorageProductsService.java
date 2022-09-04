package com.study.storage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.storage.exception.ProductNotFoundException;
import com.study.storage.model.Product;
import com.study.storage.repository.ProductsRepository;

@Service
public class StorageProductsService {

	@Autowired
	private ProductsRepository repository;
	
	public String createNewProduct(Product product) {
		
		Product productSaved = repository.save(product);
		
		System.out.println("PRODUTO SALVO: " + productSaved);
		
		return "Produto salvo com sucesso!";
	}
	
	public List<Product> getAllProducts() {
		
		return repository.findAll();
	
	}
	
	public String updateProduct(Product product) throws ProductNotFoundException {
		
		Optional<Product> productToFind = repository.findById(product.getId());
		
		if(productToFind.isEmpty()) {
			throw new ProductNotFoundException("Produto não foi encontrado no estoque!");
		}
		
		Product productUpdated = repository.save(product);
		
		System.out.println("PRODUTO ALTERADO: " + productUpdated);
		
		return "Produto alterado com sucesso!";
	}
	
	public String deleteProduct(long id) throws ProductNotFoundException {
		
		Optional<Product> productToFind = repository.findById(id);
		
		if(productToFind.isEmpty()) {
			throw new ProductNotFoundException("Produto não foi encontrado no estoque!");
		}
		
		repository.deleteById(id);
		return "Produto deletado com sucesso!";
	}
	
}
