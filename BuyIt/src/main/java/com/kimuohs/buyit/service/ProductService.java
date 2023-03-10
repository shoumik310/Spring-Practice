package com.kimuohs.buyit.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimuohs.buyit.model.Category;
import com.kimuohs.buyit.model.Product;
import com.kimuohs.buyit.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	@Autowired
	private final ProductRepository _productRepository;

	public List<Product> getAllProducts() {
		return _productRepository.findAll();
	}

	public List<Product> getAllProductsByCategoryId(int categoryId) {
		return _productRepository.findAllByCategoryId(categoryId);
	}

	public Optional<Product> getProductById(long id) {
		return _productRepository.findById(id);
	}

	public void addProduct(Product product) {
		if(product.getImageName() == null || product.getImageName().length() == 0) {
			product.setImageName("stock.jpg");
		}
		_productRepository.save(product);
	}

	public void deleteProductById(long productId) {
		_productRepository.deleteById(productId);
	}
	
}
