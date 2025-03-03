package com.example.spring.repository;

import com.example.spring.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for Expense resource
 * @author BJ - Ashis
 * */
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    /**
     * It will find the single expense from database
     * @param expenseId
     * @return Optional
     * */
    Optional<ExpenseEntity> findByExpenseId(String expenseId);

    List<ExpenseEntity> findByOwnerId(Long id);

    Optional<ExpenseEntity> findByOwnerIdAndExpenseId(Long id, String expenseId);
}
