package com.kimuohs.buyit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimuohs.buyit.model.Category;
import com.kimuohs.buyit.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	@Autowired
	private final CategoryRepository _categoryRepository;
	
	public List<Category> getAllCategories(){
		return _categoryRepository.findAll();
	}

	public Optional<Category> getCategoryById(int id) {
		return _categoryRepository.findById(id);
	}
	
	public void addCategory(Category category) {
		 _categoryRepository.save(category);
	}
	
	public void deleteCategoryById(int categoryId) {
		_categoryRepository.deleteById(categoryId);
	}
	
	
}
