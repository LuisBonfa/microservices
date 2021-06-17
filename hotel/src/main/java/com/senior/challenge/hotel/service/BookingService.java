package com.senior.challenge.hotel.service;

import com.senior.challenge.hotel.dto.BookingDTO;
import com.senior.challenge.hotel.entity.Booking;
import com.senior.challenge.hotel.enums.BookingStatus;
import com.senior.challenge.hotel.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Autowired
    private final BookingRepository bookingRepository;


    public Booking save(BookingDTO bookingDTO) {
        try {

            Booking booking = Booking.create(bookingDTO);
            booking.setCreationDate();
            booking.setStatus(BookingStatus.NEW);
            bookingRepository.save(booking);
            return booking;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Creating User", e);
        }
    }
}
