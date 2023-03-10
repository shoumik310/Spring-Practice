package com.kimuohs.buyit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kimuohs.buyit.global.GlobalData;
import com.kimuohs.buyit.service.CategoryService;
import com.kimuohs.buyit.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	@Autowired
	private final CategoryService categoryService;
	@Autowired
	private final ProductService productService;

	@GetMapping({ "/", "/home" })
	public String getHome(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}

	@GetMapping("/shop")
	public String getShop(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("products", productService.getAllProducts());
		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String getShopByCategory(@PathVariable("id") int categoryId, Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("products", productService.getAllProductsByCategoryId(categoryId));
		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String getViewProduct(@PathVariable("id") int id, Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("product", productService.getProductById(id).get());
		return "viewProduct";
	}
}
