package com.kimuohs.buyit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kimuohs.buyit.model.Category;
import com.kimuohs.buyit.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
	private final CategoryService _categoryService; 
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", _categoryService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCategoriesAdd(Model model) {
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCategoriesAdd(@ModelAttribute("category") Category category) {
		_categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
}
