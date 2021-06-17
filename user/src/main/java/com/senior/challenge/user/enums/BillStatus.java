package com.senior.challenge.user.enums;

public enum BillStatus {
    WAITING("wait"),
    PAID("paid");

    private String status;

    BillStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
