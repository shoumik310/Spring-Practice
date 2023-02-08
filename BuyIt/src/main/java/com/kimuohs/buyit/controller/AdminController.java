package com.kimuohs.buyit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kimuohs.buyit.dto.ProductDTO;
import com.kimuohs.buyit.model.Category;
import com.kimuohs.buyit.service.CategoryService;
import com.kimuohs.buyit.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller // Controller since we are using view
@RequiredArgsConstructor
public class AdminController {

	@Autowired
	private final CategoryService _categoryService;
	@Autowired
	private final ProductService _productService;

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	// Categories Section
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", _categoryService.getAllCategories());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getCategoriesAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postCategoriesAdd(@ModelAttribute("category") Category category) {
		_categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable Integer id) {
		_categoryService.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable Integer id, Model model) {
		Optional<Category> category = _categoryService.getCategoryById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else {
			return "404";
		}
	}
	
	// Product Section
	
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		model.addAttribute("products", _productService.getAllProducts());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String getProductsAdd(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", _categoryService.getAllCategories());
		return "productsAdd";
	}
	
	
}
