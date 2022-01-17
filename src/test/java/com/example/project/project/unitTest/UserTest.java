package com.example.project.project.unitTest;

import com.example.project.project.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

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
        User user = new User();
        user.setFirstName("");
        user.setLastName(null);
        user.setEmail("patoLucas.com");

        List<String> messages = List.of("This field must not be null nor blank", "Email format not valid");
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());
        assertEquals(3, violations.size());
        violations.stream().allMatch(violation -> messages.stream().anyMatch(message -> message == violation.getMessageTemplate()));
    }
}