package com.kimuohs.buyit.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimuohs.buyit.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByCategoryId(int categoryId);
}
