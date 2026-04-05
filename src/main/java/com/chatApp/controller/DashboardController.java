package com.chatApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatApp.dto.DashboardResponse;
import com.chatApp.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private DashboardService service;

	// 🔥 GET SUMMARY
	@GetMapping("/summary")
	public DashboardResponse getSummary() {
		return service.getSummary();
	}
}