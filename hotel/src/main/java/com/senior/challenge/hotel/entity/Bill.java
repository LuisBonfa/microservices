package com.senior.challenge.hotel.entity;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.senior.challenge.hotel.persistence.Updatable;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@Table(name="bill")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Bill extends Updatable {

    @Column(name="booking_id")
    private UUID bookingId;

    @Column(name="dailies_price")
    private Double dailiesPrice;

    @Type(type = "jsonb")
    @Column(name="products_data", columnDefinition = "jsonb")
    private JSONPObject products;

    @Column(name="products_price")
    private Double productsPrice;

    @Column(name="total")
    private Double total;

    @Column(name="status")
    private String status;
}
