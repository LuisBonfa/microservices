package com.senior.challenge.user.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * This is the standard error class to all business domain classes.
 */

@ResponseStatus
public class StatusNotFoundException extends ResponseStatusException {


    public StatusNotFoundException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public StatusNotFoundException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    @Override
    public String getMessage() {
        return this.getCause().getLocalizedMessage();
    }


}
