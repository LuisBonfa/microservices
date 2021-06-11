package com.senior.challenge.user.controller.error;

import com.senior.challenge.user.error.StandardException;

public class UpdateUserFailureException extends StandardException {
    public UpdateUserFailureException(String message, Throwable cause) {
        super("7c57d12d-aab4-4ce8-b42a-d1807fa17aaa", 500, message, cause);
    }
}
