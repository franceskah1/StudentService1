package com.example.studentservice.excetions;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException() {
        super();
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }
}





