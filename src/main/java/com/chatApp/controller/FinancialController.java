package com.chatApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.dto.FinancialRecordRequest;
import com.chatApp.dto.FinancialRecordResponse;
import com.chatApp.service.FinancialService;

@RestController
@RequestMapping("/finance")
public class FinancialController {

	@Autowired
	private FinancialService service;

	// 🔥 ADD RECORD
	@PostMapping("/add/{userId}")
	public FinancialRecordResponse addRecord(@PathVariable Long userId, @RequestBody FinancialRecordRequest request) {

		return service.addRecord(userId, request);
	}

	// 🔥 GET USER RECORDS
	@GetMapping("/user/{userId}")
	public List<FinancialRecordResponse> getUserRecords(@PathVariable Long userId) {
		return service.getUserRecords(userId);
	}
}