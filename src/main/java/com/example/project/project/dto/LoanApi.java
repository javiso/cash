package com.example.project.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class LoanApi implements Serializable {
    private Long id;
    private BigDecimal total;
    private Long idUser;
}
