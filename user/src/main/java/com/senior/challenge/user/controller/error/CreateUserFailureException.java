package com.senior.challenge.user.controller.error;

import com.senior.challenge.user.error.StandardException;

public class CreateUserFailureException extends StandardException {
    public CreateUserFailureException(String message, Throwable cause) {
        super("345e0b42-d35b-4f10-be4e-f6dc0fe0c99c", 500, message, cause);
    }
}
