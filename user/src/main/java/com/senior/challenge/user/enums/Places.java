package com.senior.challenge.user.enums;

public enum Places {
    ROOM("Quarto"),
    GARAGE("Garagem");

    private String garage;

    Places(String garage) {
        this.garage = garage;
    }

    public String getGarage() {
        return garage;
    }
}
