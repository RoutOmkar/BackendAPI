package com.chatApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.Entity.Category;
import com.chatApp.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	// 🔥 ADD CATEGORY
	@PostMapping("/add")
	public Category add(@RequestBody Category category) {
		return service.save(category);
	}

	// 🔥 GET ALL
	@GetMapping("/all")
	public List<Category> getAll() {
		return service.getAll();
	}
}