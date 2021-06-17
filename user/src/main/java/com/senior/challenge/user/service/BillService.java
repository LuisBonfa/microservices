package com.senior.challenge.user.service;

import com.senior.challenge.user.dto.BillDTO;
import com.senior.challenge.user.dto.ProductsDTO;
import com.senior.challenge.user.entity.Bill;
import com.senior.challenge.user.entity.Booking;
import com.senior.challenge.user.enums.Prices;
import com.senior.challenge.user.repository.BillRepository;
import com.senior.challenge.user.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill calculateBill(Booking booking, ProductsDTO productsDTO) {

        // Validar quantas diárias e quantas são em dias de semana e finais de semana
        // Validar

        try {
            var begin = Calendar.getInstance();
            var end = Calendar.getInstance();
            var bill = new BillDTO();

            var garagePrice = 0.0;
            var dailiesPrice = 0.0;
            final double[] productsPrice = {0.0};

            begin.setTime(booking.getBegin());
            end.setTime(booking.getEnd());

            long time = booking.getEnd().getTime() - booking.getBegin().getTime();
            long days = TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS);

            for (var i = 0; i < days; i++) {
                if (Validator.verifyWeekend(begin)) {
                    dailiesPrice += Prices.ROOM_WEEKEND.getPrice();
                    garagePrice += (Boolean.TRUE.equals(booking.getGarage())) ? Prices.GARAGE_WEEKEND.getPrice() : 0;
                } else {
                    dailiesPrice += Prices.ROOM_WEEKDAY.getPrice();
                    garagePrice += (Boolean.TRUE.equals(booking.getGarage())) ? Prices.GARAGE_WEEKDAY.getPrice() : 0;
                }

                begin.add(Calendar.DAY_OF_WEEK, 1);
            }

            begin.setTime(booking.getBegin());

            if (new Date().after(booking.getEnd())) {
                begin.add(Calendar.DAY_OF_WEEK, 1);
                if (Validator.verifyWeekend(begin)) {
                    dailiesPrice += Prices.ROOM_WEEKEND.getPrice();
                } else {
                    dailiesPrice += Prices.ROOM_WEEKDAY.getPrice();
                }
            }

            if (!productsDTO.getProducts().isEmpty()) {
                productsDTO.getProducts().forEach((k, v) -> {
                    var price = Prices.valueOf(k.toUpperCase());
                    productsPrice[0] += price.getPrice() * (int) v;
                });
            }

            bill.setBooking(booking);
            bill.setDailiesPrice(dailiesPrice);
            bill.setProductsPrice(productsPrice[0]);
            bill.setGaragePrice(garagePrice);
            bill.setStatus("waiting");
            bill.setTotal(dailiesPrice + garagePrice + productsPrice[0]);
            bill.setProducts(productsDTO.getProducts());

            return save(bill);

        } catch (PersistenceException pe) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Checking out", pe);
        }
    }

    public Bill save(BillDTO billDTO) {
        var bill = Bill.create(billDTO);
        bill.setCreationDate();
        billRepository.save(bill);

        return bill;
    }
}
