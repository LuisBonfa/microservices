package com.senior.challenge.hotel.dto;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.senior.challenge.hotel.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private UUID userId;
    private String begin;
    private String end;
    private BookingStatus status;
}
