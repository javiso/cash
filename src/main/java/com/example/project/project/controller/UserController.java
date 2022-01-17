package com.example.project.project.controller;

import com.example.project.project.Service.UserService;
import com.example.project.project.dto.UserApi;
import com.example.project.project.model.User;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cash/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final MapperFacade mapper;

    @GetMapping("/{idUser}")
    public ResponseEntity<UserApi> findById(@PathVariable(name = "idUser") final Long idUser) {
        return ResponseEntity.ok(mapper.map(userService.findById(idUser), UserApi.class));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody final User user) {
        return new ResponseEntity(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable(name= "idUser") final Long idUser){
        userService.deleteUser(idUser);
    }
}