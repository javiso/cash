package com.example.project.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
public class UserApi implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<LoanApi> loans;
}
