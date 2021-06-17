package com.senior.challenge.hotel.enums;

public enum BookingStatus {
    NEW("new"),
    ACCEPTED("accepted"),
    REJECTED("rejected"),
    CANCELED("canceled");

    private String status;

    BookingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
