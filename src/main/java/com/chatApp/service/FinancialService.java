package com.chatApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.Entity.Category;
import com.chatApp.Entity.FinancialRecord;
import com.chatApp.Entity.RoleType;
import com.chatApp.Entity.TransactionType;
import com.chatApp.Entity.User;
import com.chatApp.Entity.UserRole;
import com.chatApp.Repository.CategoryRepository;
import com.chatApp.Repository.FinancialRecordRepository;
import com.chatApp.Repository.UserRepository;
import com.chatApp.Repository.UserRoleRepository;
import com.chatApp.dto.FinancialRecordRequest;
import com.chatApp.dto.FinancialRecordResponse;

@Service
public class FinancialService {

	@Autowired
	private FinancialRecordRepository recordRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserRoleRepository userRoleRepo;
	@Autowired
	private CategoryRepository categoryRepo;

	public FinancialRecordResponse addRecord(Long userId, FinancialRecordRequest request) {

		// ✅ Get User
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// ✅ Get Role
		UserRole userRole = userRoleRepo.findByUser(user);
		RoleType role = userRole.getRole().getRoleName();

		// ✅ Role check (ONLY ADMIN & ANALYST allowed)
		if (role == RoleType.ADMIN && role != RoleType.VIEWER) {
			throw new RuntimeException("Access denied! Only ADMIN or ANALYST can add records");
		}

		// ✅ Get Category
		Category category = categoryRepo.findById(request.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category not found"));

		// ✅ Create Record
		FinancialRecord record = new FinancialRecord();

		record.setUser(user); // 🔥 IMPORTANT FIX
		record.setCategory(category);
		record.setAmount(request.getAmount());
		record.setDescription(request.getDescription());
		record.setType(TransactionType.valueOf(request.getType()));

		// ✅ Use request date
		record.setTransactionDate(request.getTransactionDate());

		record.setCreatedAt(LocalDateTime.now());

		FinancialRecord saved = recordRepo.save(record);

		return mapToResponse(saved);
	}

	// 🔥 GET USER RECORDS
	public List<FinancialRecordResponse> getUserRecords(Long userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		return recordRepo.findByUser(user).stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	// 🔥 DTO MAPPING
	private FinancialRecordResponse mapToResponse(FinancialRecord record) {

		FinancialRecordResponse res = new FinancialRecordResponse();
		res.setId(record.getId());
		res.setType(record.getType().name());
		res.setAmount(record.getAmount());
		res.setDescription(record.getDescription());
		res.setCategoryName(record.getCategory().getName());
		res.setDate(record.getTransactionDate());

		return res;
	}
}