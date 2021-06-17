package com.senior.challenge.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.senior.challenge.user.dto.BookingDTO;
import com.senior.challenge.user.enums.BookingStatus;
import com.senior.challenge.user.persistence.Updatable;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="booking")
public class Booking extends Updatable {

    @NotNull(message = "Usuario é obrigatório")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "user_booking_fk"))
    private User user;

    @Column(name = "check_in")
    private Date checkIn;

    @Column(name = "check_out")
    private Date checkOut;

    @NotNull(message = "Data Inicial é obrigatória")
    @Column(name = "booking_begin")
    private Date begin;

    @NotNull(message = "Data Final é obrigatória")
    @Column(name = "booking_end")
    private Date end;

    @Column(name = "garage")
    private Boolean garage;

    @NotNull(message = "Status é obrigatório")
    @Column(name = "status")
    private String status;

    @JsonBackReference
    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bill> bill;

    public static Booking create(BookingDTO bookingDTO) {
        return new ModelMapper().map(bookingDTO, Booking.class);
    }
}
