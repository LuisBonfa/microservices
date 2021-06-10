package com.senior.challenge.user.error.bean;

import java.io.Serializable;

public class Message extends StandardMessage implements Serializable {
    public Message() {
        super();
    }

    public Message(String code, int httpStatus, String message, String stackTrace) {
        super(code, httpStatus, message, stackTrace);
    }
}