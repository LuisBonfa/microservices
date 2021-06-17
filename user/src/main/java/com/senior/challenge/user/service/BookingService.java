package com.senior.challenge.user.service;

import com.senior.challenge.user.dto.BookingDTO;
import com.senior.challenge.user.dto.ProductsDTO;
import com.senior.challenge.user.entity.Bill;
import com.senior.challenge.user.entity.Booking;
import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.enums.BookingStatus;
import com.senior.challenge.user.persistence.DateTreatment;
import com.senior.challenge.user.repository.BookingRepository;
import com.senior.challenge.user.repository.UserRepository;
import com.senior.challenge.user.utils.VerifyDtoFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.util.*;

@Service
public class BookingService extends DateTreatment {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final BillService billService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, BillService billService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.billService = billService;
    }

    public Booking save(BookingDTO bookingDTO) {
        try {
            Optional<User> user = userRepository.findById(bookingDTO.getUserId());

            if (user.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");

            List<Booking> bookingCreated = bookingRepository.findRegisteredBooking(user.get(), bookingDTO.getBegin(),
                    bookingDTO.getEnd());

            if (!bookingCreated.isEmpty())
                throw new ResponseStatusException(HttpStatus.FOUND, "Booking already registered");


            var booking = Booking.create(bookingDTO);
            booking.setId(null);
            booking.setUser(user.get());
            booking.setBegin(bookingDTO.getBegin());
            booking.setEnd(bookingDTO.getEnd());
            booking.setGarage(bookingDTO.getGarage());
            booking.setStatus(BookingStatus.NEW.getStatus());

            booking.setCreationDate();

            bookingRepository.save(booking);
            return booking;

        } catch (PersistenceException pe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Creating Booking", pe);
        }
    }

    public List<Booking> findAll() {
        try {
            return bookingRepository.findAll();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Listing Bookings", e);
        }
    }

    public Booking findById(UUID bookingId) {
        try {
            Optional<Booking> booking = bookingRepository.findById(bookingId);

            if (booking.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking Not Found");

            return booking.get();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error finding booking", e);
        }
    }

    public Booking update(BookingDTO bookingDTO, UUID bookingId) {

        try {
            Optional<Booking> booking = bookingRepository.findById(bookingId).map(bookingUpdated -> {
                VerifyDtoFields.verifyNullAndAddToObject(bookingDTO, bookingUpdated);
                bookingUpdated.setUpdatedAt(new Date());
                bookingRepository.save(bookingUpdated);
                return bookingUpdated;
            });

            if (booking.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking Not Found");

            return booking.get();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Updating User", e);
        }
    }

    public Bill checkOut(ProductsDTO productsDTO, UUID bookingId) {
        try {
            Optional<Booking> booking = bookingRepository.findById(bookingId).map(bookingUpdated -> {
                bookingUpdated.setCheckOut(new Date());
                bookingUpdated.setUpdatedAt(new Date());
                bookingUpdated.setStatus(BookingStatus.ENDED.getStatus());
                bookingRepository.save(bookingUpdated);
                return bookingUpdated;
            });

            if (booking.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking Not Found");

            return billService.calculateBill(booking.get(), productsDTO);

        } catch (PersistenceException pe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error checking out booking", pe);
        } catch (NullPointerException ne) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", ne);
        }
    }

    public Booking checkIn(UUID bookingId) {
        try {
            Optional<Booking> booking = bookingRepository.findById(bookingId);

            if (booking.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking Not Found");

            booking.map(bookingUpdated -> {
                bookingUpdated.setCheckIn(new Date());
                bookingUpdated.setUpdatedAt(new Date());
                bookingUpdated.setBegin(booking.get().getBegin());
                bookingRepository.save(bookingUpdated);
                return bookingUpdated;
            });


            return booking.get();

        } catch (PersistenceException pe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error checking In", pe);
        }
    }
}
