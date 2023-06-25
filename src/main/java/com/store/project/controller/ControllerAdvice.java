package com.store.project.controller;

import com.store.project.exception.*;
import com.store.project.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ErrorResponse handleProductAlreadyExistsException(ProductAlreadyExistsException productAlreadyExistsException) {
        log.error(productAlreadyExistsException.getMessage());
        return new ErrorResponse(productAlreadyExistsException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        log.error(productNotFoundException.getMessage());
        return new ErrorResponse(productNotFoundException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ProductValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleProductValidationException(ProductValidationException productValidationException) {
        log.error(productValidationException.getMessage());
        return new ErrorResponse(productValidationException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(StoreAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ErrorResponse handleStoreAlreadyExistsException(StoreAlreadyExistsException storeAlreadyExistsException) {
        log.error(storeAlreadyExistsException.getMessage());
        return new ErrorResponse(storeAlreadyExistsException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleStoreNotFoundException(StoreNotFoundException storeNotFoundException) {
        log.error(storeNotFoundException.getMessage());
        return new ErrorResponse(storeNotFoundException.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(StoreValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleStoreValidationException(StoreValidationException storeValidationException) {
        log.error(storeValidationException.getMessage());
        return new ErrorResponse(storeValidationException.getMessage(), LocalDateTime.now());
    }

}
