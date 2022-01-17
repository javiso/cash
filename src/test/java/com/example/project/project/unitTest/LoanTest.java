package com.example.project.project.unitTest;

import com.example.project.project.model.Loan;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoanTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    public void userDataTest(){
        Loan loan = new Loan();
        loan.setTotal(new BigDecimal("-4.5"));

        List<String> messages = List.of("This field must not be null nor blank", "Email format not valid");
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<Loan> constraintViolation = (ConstraintViolation<Loan>) violations.toArray()[0];
    }
}