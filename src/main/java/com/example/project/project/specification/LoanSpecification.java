package com.example.project.project.specification;

import com.example.project.project.model.Loan;
import com.example.project.project.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public final class LoanSpecification {

    public static Specification<Loan> userIdContain(Long idUser) {
        return (root, query, criteriaBuilder) -> {
            Join<Loan, User> userJoin = root.join("user");
            return criteriaBuilder.equal(userJoin.get("id"), idUser);
        };
    }
}