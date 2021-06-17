package com.senior.challenge.hotel.dto;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {

    private UUID bookingId;
    private Double dailiesPrice;
    private JSONPObject productsData;
    private Double productsPrice;
    private Double total;
    private String status;
}
