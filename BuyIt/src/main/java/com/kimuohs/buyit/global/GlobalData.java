package com.kimuohs.buyit.global;

import java.util.ArrayList;
import java.util.List;

import com.kimuohs.buyit.model.Product;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart = new ArrayList<>();
	}
}
