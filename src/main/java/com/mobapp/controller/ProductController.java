package com.mobapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController {

	@RequestMapping("/productList/all")
	public String getProducts(Model model) {

		return "productList";
	}

	@RequestMapping("/viewProduct/{productId}")
	public String viewProduct(@PathVariable int productId, Model model) throws IOException {

		return "viewProduct";
	}

	@RequestMapping("/productList")
	public String getProductByCategory(@RequestParam("searchCondition") String searchCondition, Model model) {

		return "productList";
	}

}
