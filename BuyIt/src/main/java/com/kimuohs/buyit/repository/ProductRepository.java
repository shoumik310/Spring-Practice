package com.kimuohs.buyit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimuohs.buyit.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
