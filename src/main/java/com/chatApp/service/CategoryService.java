package com.chatApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.Entity.Category;
import com.chatApp.Repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category save(Category category) {
		return repo.save(category);
	}

	public List<Category> getAll() {
		return repo.findAll();
	}
}