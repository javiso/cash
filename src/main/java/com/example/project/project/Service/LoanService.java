package com.example.project.project.Service;

import com.example.project.project.model.Loan;
import com.example.project.project.repository.LoanRepository;
import com.example.project.project.specification.LoanSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public Page<Loan> findLoans(final Long idUser, final Pageable pageable) {
        log.info("Fetching loans of user whose id is {}", idUser);
        Specification<Loan> specificationLoan = Specification.where(idUser == null ? null : LoanSpecification.userIdContain(idUser));

        return loanRepository.findAll(specificationLoan, pageable);
       }
}