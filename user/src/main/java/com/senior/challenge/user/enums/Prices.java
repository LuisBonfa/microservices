package com.senior.challenge.user.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Prices {
    WEEKDAY_ROOM_PRICE(1,120),
    WEEKEND_ROOM_PRICE(2,150),
    WEEKDAY_GARAGE_PRICE(3,15),
    WEEKEND_GARAGE_PRICE(4,20);

    private int id;
    private int price;

    Prices(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}
