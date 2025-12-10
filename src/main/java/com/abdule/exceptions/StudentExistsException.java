package com.abdule.exceptions;

public class StudentExistsException extends RuntimeException {
    public StudentExistsException(String message) {
        super(message);
    }
}
