package com.senior.challenge.hotel.entity;

import com.senior.challenge.hotel.dto.BookingDTO;
import com.senior.challenge.hotel.enums.BookingStatus;
import com.senior.challenge.hotel.persistence.Updatable;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name="booking")
public class Booking extends Updatable {

    @NotNull(message = "Usuario é obrigatório")
    @Column(name = "user_id")
    private UUID userId;

    @NotNull(message = "Data de check-in é obrigatória")
    @Column(name = "check_in")
    private Date checkIn;

    @NotNull(message = "Data de check-out é obrigatória")
    @Column(name = "check_out")
    private Date checkOut;

    @NotNull(message = "Data Inicial é obrigatória")
    @Column(name = "begin")
    private Date begin;

    @NotNull(message = "Data Final é obrigatória")
    @Column(name = "end")
    private Date end;

    @NotNull(message = "Status é obrigatório")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public static Booking create(BookingDTO bookingDTO) {
        return new ModelMapper().map(bookingDTO, Booking.class);
    }
}
