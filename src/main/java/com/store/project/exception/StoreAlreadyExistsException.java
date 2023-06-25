package com.store.project.exception;

public class StoreAlreadyExistsException extends RuntimeException {
    public StoreAlreadyExistsException(String message) {
        super(message);
    }
}
