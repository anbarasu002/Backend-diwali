package com.example.Backend.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}
