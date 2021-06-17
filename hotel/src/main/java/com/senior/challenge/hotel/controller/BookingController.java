package com.senior.challenge.hotel.controller;

import com.senior.challenge.hotel.dto.BookingDTO;
import com.senior.challenge.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.save(bookingDTO));
    }
}
