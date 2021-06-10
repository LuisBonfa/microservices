package com.senior.challenge.user.service.error;

import com.senior.challenge.user.error.StandardException;

public class RoleSaveFailureException extends StandardException {
    public RoleSaveFailureException(String message) {
        super("345e0b42-d35b-4f10-be4e-f6dc0fe0c99c", 500, message);
    }
}
