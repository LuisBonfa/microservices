package com.senior.challenge.user.error;


import com.google.common.base.Throwables;
import com.senior.challenge.user.error.bean.Message;

import javax.persistence.PersistenceException;
import java.lang.reflect.InvocationTargetException;

/**
 * This is the standard error class to all business domain classes.
 */
public class StandardException extends Exception {

    private int httpStatus;
    private String code;

    public StandardException(String code, int httpStatus, String message) {
        super(message);
        this.code = "ERROR:" + code;
        this.httpStatus = httpStatus;
    }

    public StandardException(String code, int httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.code = "ERROR:" + code;
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public Message bean() {
        Message b = new Message();
        b.setCode(getCode());
        b.setHttpStatus(getHttpStatus());
        b.setMessage(getMessage());
        b.setStackTrace(Throwables.getStackTraceAsString(this));

        return b;
    }
}
