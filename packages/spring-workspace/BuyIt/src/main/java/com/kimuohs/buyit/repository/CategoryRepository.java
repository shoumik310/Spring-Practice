package com.kimuohs.buyit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimuohs.buyit.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
