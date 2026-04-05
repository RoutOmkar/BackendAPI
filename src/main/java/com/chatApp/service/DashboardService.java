package com.chatApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApp.Entity.TransactionType;
import com.chatApp.Repository.FinancialRecordRepository;
import com.chatApp.dto.DashboardResponse;

@Service
public class DashboardService {

	@Autowired
	private FinancialRecordRepository repo;

	public DashboardResponse getSummary() {

		Double income = repo.getTotalByType(TransactionType.INCOME);
		Double expense = repo.getTotalByType(TransactionType.EXPENSE);

		if (income == null)
			income = 0.0;
		if (expense == null)
			expense = 0.0;

		DashboardResponse res = new DashboardResponse();
		res.setTotalIncome(income);
		res.setTotalExpense(expense);
		res.setBalance(income - expense);

		return res;
	}
}