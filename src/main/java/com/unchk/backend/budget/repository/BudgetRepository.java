package com.unchk.backend.budget.repository;

import com.unchk.backend.budget.entity.Budget;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository
        extends JpaRepository<
        Budget,
        Long
        > {
}