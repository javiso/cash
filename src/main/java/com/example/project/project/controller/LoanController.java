package com.example.project.project.controller;

import com.example.project.project.Service.LoanService;
import com.example.project.project.dto.LoanApi;
import com.example.project.project.dto.PageApi;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cash/v1/loan")
@AllArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final MapperFacade mapperFacade;

    @GetMapping
    public ResponseEntity<PageApi<LoanApi>> findAll(
            @PageableDefault(size = 5) final Pageable pageable,
            @RequestParam(name= "id_user", required = false)  final Long idUser) {

        return ResponseEntity.ok(mapperFacade.map(loanService.findLoans(idUser, pageable), PageApi.class));
    }
}