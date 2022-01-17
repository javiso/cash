package com.example.project.project.repository;

import com.example.project.project.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoanRepository extends JpaRepository<Loan, Long> , JpaSpecificationExecutor<Loan> {
}
