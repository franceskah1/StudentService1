package com.example.studentservice.excetions;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException() {
        super();
    }

    public DuplicateEmailException(String message) {
        super(message);
    }
}


