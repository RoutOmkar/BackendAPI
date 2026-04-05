package com.chatApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatApp.Entity.Category;
import com.chatApp.Entity.TransactionType;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByType(TransactionType type);
}