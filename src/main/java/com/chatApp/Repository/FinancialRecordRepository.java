package com.chatApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chatApp.Entity.FinancialRecord;
import com.chatApp.Entity.TransactionType;
import com.chatApp.Entity.User;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

	List<FinancialRecord> findByUser(User user);

	List<FinancialRecord> findByType(TransactionType type);

	List<FinancialRecord> findByUserAndType(User user, TransactionType type);

	// ✅ FIXED METHOD
	@Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = :type")
	Double getTotalByType(@Param("type") TransactionType type);
}