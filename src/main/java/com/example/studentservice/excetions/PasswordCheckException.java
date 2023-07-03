package com.example.studentservice.excetions;

public class PasswordCheckException extends RuntimeException{
    public PasswordCheckException() {
        super();
    }

    public PasswordCheckException(String message) {
        super(message);
    }
}

