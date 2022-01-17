package com.example.project.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
        final List<String> validationsErrors = exception.getBindingResult().getFieldErrors().stream().map(
                violation -> violation.getField() + ":" + violation.getDefaultMessage()).collect(Collectors.toList());
        log.error("Excpetion. Please, see the following stacktrace: ", exception);
        ErrorApi apiError = new ErrorApi(HttpStatus.BAD_REQUEST, "Parameters Not valid",validationsErrors);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<ErrorApi> handleEntityNotFound(Exception exception) {
        log.error("Excpetion. Please, see the following stacktrace: ", exception);
        ErrorApi apiError = new ErrorApi(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}